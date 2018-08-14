# VolumeView
A library which contains a volume view. This view represents percentage value as volume slider graphically.

<p align="center">
<img src ="https://github.com/vladimirpetrovski/VolumeView/blob/master/screenshots/screenshot-sample.png" width="350"/>
</p>

## Download
```
implementation 'com.vladimirpetrovski.android:volumeview:0.0.2'
```

## Usage
```
<com.vladimirpetrovski.volumeview.VolumeView
    android:layout_width="180dp"
    android:layout_height="250dp"
    app:volumeView_numOfLines="15"
    app:volumeView_selectedColorLines="#3F51B5"
    app:volumeView_unselectedColorLines="#aaaaaa"
    app:volumeView_volume="45"/>
```
Use setters in order to change values:
```
volumeView.setVolume(percentage);
volumeView.setNumOfLines(lines);
volumeView.setSelectedColorLines(newColor);
volumeView.setUnselectedColorLines(newColor);
```

## Authors
* Vladimir Petrovski (https://github.com/vladimirpetrovski)

## Licence
```
The MIT License (MIT)

Copyright (c) 2018 Vladimir Petrovski

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
