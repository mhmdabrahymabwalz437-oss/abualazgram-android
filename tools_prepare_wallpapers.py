from pathlib import Path
from PIL import Image

root = Path('/home/ubuntu/abualazgram')
assets = root / 'assets'
output = root / 'TMessagesProj' / 'src' / 'main' / 'res' / 'drawable-nodpi'
output.mkdir(parents=True, exist_ok=True)

for name in ('obsidian_gold', 'royal_blue', 'platinum_black'):
    source = assets / f'wallpaper_{name}.png'
    target = output / f'abualazgram_wallpaper_{name}.jpg'
    image = Image.open(source).convert('RGB')
    image.thumbnail((720, 1280), Image.Resampling.LANCZOS)
    image.save(target, format='JPEG', quality=88, optimize=True, progressive=True)
    print(f'created {target} {image.size}')
