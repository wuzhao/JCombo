**This application is mainly used in AliExpress.com F2E Team, but it should work for anyone else.**

JCombo
=========

*The Java GUI Front-End for YCombo.*

Requirement
-----------

This application runs under Microsoft Windows, Linux and Macintosh, depends on [Java Runtime Environment](http://www.java.com/getjava/) 6.0+.

Installation
------------

Download the latest [zip package](http://wuzhao.github.com/JCombo/jcombo-latest.zip), extract all contents to a proper folder (ex. C:\Program Files\JCombo\), then run the execution file and enjoy.

	java -jar jcombo.jar

Usage
-----

JCombo combines files starting from a seed file. A seed file could be a JS file or CSS file, and using one of the follow special one-line comments to specify its dependent files. Details about [Dependencies Management](https://github.com/nqdeng/YCombo/blob/master/README.md#dependencies-management).

You could click to add inputs, or drag-and-drop the files to add them, supported filename extension: `.js.seed` and `.css.seed`.

After all inputs being added, you could adjust the combo options and click the "Combo!" button to start combination. Option details could be found in [YCombo](https://github.com/nqdeng/YCombo) document.

Uninstallation
--------------

Just remember to remove context menu before removing the folder where this application is installed to, that's all.

Related Tools
-----------

* [YCombo](https://github.com/nqdeng/YCombo): The core of JCombo, it is a command line application.
* [YComboGUI](https://github.com/nqdeng/YComboGUI): Windows only, depends on [Microsoft .NET Framework 2.0](http://www.microsoft.com/download/en/details.aspx?id=19) and [Java Runtime Environment](http://www.java.com/getjava/).

License
-------

JCombo is released under the MIT license:

>Copyright (c) 1999-2013 Alibaba.Com, Inc.
>
>Permission is hereby granted, free of charge, to any person obtaining a copy of
>this software and associated documentation files (the "Software"), to deal in
>the Software without restriction, including without limitation the rights to
>use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
>of the Software, and to permit persons to whom the Software is furnished to do
>so, subject to the following conditions:
>
>The above copyright notice and this permission notice shall be included in all
>copies or substantial portions of the Software.
>
>THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
>IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
>FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
>AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
>LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
>OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
>SOFTWARE.