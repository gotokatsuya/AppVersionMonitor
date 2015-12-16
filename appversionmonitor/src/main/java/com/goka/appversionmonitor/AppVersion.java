/**
 Copyright (c) 2015 muukii <m@muukii.me>
 Copyright (c) 2015 gotokatsuya

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 */

package com.goka.appversionmonitor;

import android.support.annotation.NonNull;

import java.util.regex.Pattern;

public class AppVersion implements Comparable<AppVersion> {

    public String version;

    public AppVersion(String version) {
        this.version = version;
    }

    @Override
    public int compareTo(@NonNull AppVersion another) {
        String[] splitVersion = this.version.split(Pattern.quote("."));
        String[] splitAnotherVersion = another.version.split(Pattern.quote("."));
        int count = splitAnotherVersion.length;
        int anotherCount = splitAnotherVersion.length;
        int maxCount = Math.max(count, anotherCount);
        for (int i = 0; i < maxCount; i++) {
            int l = Integer.parseInt(splitVersion[i]);
            int r = Integer.parseInt(splitAnotherVersion[i]);
            if ( l > r ) {
                return 1;
            } else if (l < r) {
                return -1;
            }
        }
        return 0;
    }

}
