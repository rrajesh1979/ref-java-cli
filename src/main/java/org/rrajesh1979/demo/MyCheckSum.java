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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

@CommandLine.Command(name = "mychecksum", mixinStandardHelpOptions = true, version = "mychecksum 0.0.8", description = "Prints the checksum (MD5 by default) of a file to STDOUT.")
@Slf4j
public class MyCheckSum implements Callable<Integer> {
    @CommandLine.Parameters(index = "0", description = "The file whose checksum to calculate.")
    private File file;

    @CommandLine.Option(names = { "-a", "--algorithm" }, description = "MD5, SHA-1, SHA-256, ...")
    private String algorithm = "MD5";

    public static void main(String[] args) {
        int exitCode;
        log.info("Hello from MyCheckSum");

        exitCode = new CommandLine(new MyCheckSum()).execute(args);
        System.exit(exitCode);

    }

    @Override
    public Integer call() throws Exception {
        String checkSum = getCheckSum(file);
        log.info("Checksum of file {}, is : {}", file.getName(), checkSum);
        return 0;
    }

    public String getCheckSum(File file) throws IOException, NoSuchAlgorithmException {
        byte[] fileContents = Files.readAllBytes(file.toPath());
        byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);

        // This bytes[] has bytes in decimal format;
        // Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
