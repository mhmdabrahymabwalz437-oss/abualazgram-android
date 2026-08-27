#!/usr/bin/env bash
set -euo pipefail

: "${API_ID:?API_ID secret is required}"
: "${API_HASH:?API_HASH secret is required}"

case "$API_ID" in
  (''|*[!0-9]*) echo "API_ID must be numeric" >&2; exit 2 ;;
esac
case "$API_HASH" in
  (''|*[!a-zA-Z0-9]*) echo "API_HASH must be alphanumeric" >&2; exit 2 ;;
esac

BUILD_VARS="TMessagesProj/src/main/java/org/telegram/messenger/BuildVars.java"
test -f "$BUILD_VARS"

python3 - "$BUILD_VARS" "$API_ID" "$API_HASH" <<'PY'
import pathlib
import sys

path = pathlib.Path(sys.argv[1])
api_id = sys.argv[2]
api_hash = sys.argv[3]
text = path.read_text()
text = text.replace('public static int APP_ID = 0;', f'public static int APP_ID = {api_id};', 1)
text = text.replace('public static String APP_HASH = "";', f'public static String APP_HASH = "{api_hash}";', 1)
if 'APP_ID = 0' in text or 'APP_HASH = ""' in text:
    raise SystemExit('BuildVars placeholders were not found')
path.write_text(text)
PY
