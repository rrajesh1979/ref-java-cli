/*
 *  Copyright 2022 The original authors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License .
 */
package org.rrajesh1979.demo;

import java.io.*;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyCheckSumTest {

    @Test
    void main() {
    }

    @Test
    void getCheckSum() throws IOException, NoSuchAlgorithmException {
        System.out.println("Testing getCheckSum");
        File tempFile = createTempDataFile();
        String calculatedCheckSum = new MyCheckSum().getCheckSum(tempFile);
        tempFile.delete();
        String expectedCheckSum = "f0ef7081e1539ac00ef5b761b4fb01b3";
        System.out.println("Calculated CheckSum : " + calculatedCheckSum);
        System.out.println("Expected CheckSum : " + expectedCheckSum);
        assertEquals(expectedCheckSum, calculatedCheckSum);
    }

    static File createTempDataFile() throws IOException {
        File tempFile = File.createTempFile("checksum", "test");
        try (FileOutputStream fileOutputStream = new FileOutputStream(tempFile)) {
            fileOutputStream.write("Hello world\n".getBytes());
            fileOutputStream.flush();
        }
        return tempFile;
    }
}
