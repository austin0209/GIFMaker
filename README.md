# GIFMaker
A straight-forward GIF capture program

Credits to Elliot Kroo for the GIF sequence writer.

## Usage:

Download the runnable JAR from the repo, or you can just click [here](https://github.com/austin0209/GIFMaker/raw/master/GIFMaker.jar).
Note that the program requires Java 8 or above.

Click "Select Region" to select a region. A selection is made by clicking and dragging.
Once the region is selected, click "Make GIF." The resulting gif will be saved in the active directory (the folder you run the JAR in).

## Parameter Info:
Use these parameters to edit the smoothness and file size of your GIF, as well as to change the semantics of the program for your  convenience.
  - **Number of Frames** - The number of frames the program will capture
  - **Time Between Frames** - The amount of time in milliseconds the program waits before capturing each frame
  - **Compression Factor** - Use this to lower the file size. The width and height of the resulting GIF will be the width and height of your selection multiplied by the compression factor (higher = less compression, smaller dimensions/file size)
  - **File Name** - The file name of your resulting GIF. This is saved in the active directory (the directory that you run the program in).
  - **Countdown Time** - The amount of time in seconds the program waits before beginning the capture process
