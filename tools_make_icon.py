from pathlib import Path
from PIL import Image

source = Path('/home/ubuntu/upload/112304.jpg')
root = Path('/home/ubuntu/abualazgram/TMessagesProj/src/main/res')
img = Image.open(source).convert('RGB')
# Fit the full supplied artwork inside a square, preserving its proportions.
size = 1024
canvas = Image.new('RGB', (size, size), (0, 0, 0))
scale = min(size / img.width, size / img.height)
resized = img.resize((round(img.width * scale), round(img.height * scale)), Image.Resampling.LANCZOS)
canvas.paste(resized, ((size - resized.width) // 2, (size - resized.height) // 2))
for density, px in [('mdpi', 48), ('hdpi', 72), ('xhdpi', 96), ('xxhdpi', 144), ('xxxhdpi', 192)]:
    out = canvas.resize((px, px), Image.Resampling.LANCZOS)
    for name in ('ic_launcher.png', 'ic_launcher_round.png', 'icon_6_launcher.png', 'icon_6_launcher_round.png'):
        out.save(root / f'mipmap-{density}' / name, optimize=True)
Path('/home/ubuntu/abualazgram/assets').mkdir(exist_ok=True)
canvas.save('/home/ubuntu/abualazgram/assets/abualazgram-branding-source.png', optimize=True)
