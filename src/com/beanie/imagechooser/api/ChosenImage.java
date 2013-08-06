/*******************************************************************************
 * Copyright 2013 Kumar Bibek
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *    
 * http://www.apache.org/licenses/LICENSE-2.0
 * 	
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package com.beanie.imagechooser.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;

public class ChosenImage {
    private String filePathOriginal;

    private String fileThumbnail;

    private String fileThumbnailSmall;

    public String getFilePathOriginal() {
        return filePathOriginal;
    }

    public void setFilePathOriginal(String filePathOriginal) {
        this.filePathOriginal = filePathOriginal;
    }

    public String getFileThumbnail() {
        return fileThumbnail;
    }

    public void setFileThumbnail(String fileThumbnail) {
        this.fileThumbnail = fileThumbnail;
    }

    public String getFileThumbnailSmall() {
        return fileThumbnailSmall;
    }

    public void setFileThumbnailSmall(String fileThumbnailSmall) {
        this.fileThumbnailSmall = fileThumbnailSmall;
    }

    public String getOriginalImageHeight() {
        String height = "";
        try {
            ExifInterface exif = new ExifInterface(filePathOriginal);
            height = exif.getAttribute(ExifInterface.TAG_IMAGE_LENGTH);
            if (height.equals("0")) {
                height = Integer.toString(getBitmap().get().getHeight());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return height;
    }

    private SoftReference<Bitmap> getBitmap() {
        SoftReference<Bitmap> bitmap = null;
        try {
            bitmap = new SoftReference<Bitmap>(BitmapFactory.decodeStream(new FileInputStream(
                    new File(filePathOriginal))));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public String getOriginalImageWidth() {
        String width = "";
        try {
            ExifInterface exif = new ExifInterface(filePathOriginal);
            width = exif.getAttribute(ExifInterface.TAG_IMAGE_WIDTH);
            if (width.equals("0")) {
                width = Integer.toString(getBitmap().get().getWidth());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return width;
    }

    public String getFileExtension() {
        return FileUtils.getFileExtension(filePathOriginal);
    }

}
