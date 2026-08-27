from pathlib import Path
from PIL import Image

root = Path('/home/ubuntu/abualazgram')
source = root / 'assets' / 'abualazgram-icon-master.png'
img = Image.open(source).convert('RGB')
size = min(img.size)
left = (img.width - size) // 2
top = (img.height - size) // 2
img = img.crop((left, top, left + size, top + size))
for density, pixels in {'mdpi': 48, 'hdpi': 72, 'xhdpi': 96, 'xxhdpi': 144, 'xxxhdpi': 192}.items():
    out = root / 'TMessagesProj' / 'src' / 'main' / 'res' / f'mipmap-{density}'
    out.mkdir(parents=True, exist_ok=True)
    resized = img.resize((pixels, pixels), Image.Resampling.LANCZOS)
    for name in ('ic_launcher.png', 'ic_launcher_round.png', 'icon_6_launcher.png', 'icon_6_launcher_round.png'):
        resized.save(out / name, optimize=True)
print('Generated ABUALAZGRAM icon assets from the new attachment.')
