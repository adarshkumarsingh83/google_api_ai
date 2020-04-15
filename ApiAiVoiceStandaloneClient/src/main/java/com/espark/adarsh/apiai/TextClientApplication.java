package com.espark.adarsh.apiai;

/***********************************************************************************************************************
 *
 * API.AI Java SDK - client-side libraries for API.AI
 * =================================================
 *
 * Copyright (C) 2016 by Speaktoit, Inc. (https://www.speaktoit.com) https://www.api.ai
 *
 * *********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 ***********************************************************************************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import com.espark.adarsh.apiai.util.PropertiesReader;

/**
 * Text client reads requests line by line from stdandart input.
 */
public class TextClientApplication {

    private static final String INPUT_PROMPT = PropertiesReader.getProperty("application.input.prompt");

    private static final int ERROR_EXIT_CODE = 1;

    public static void main(String[] args) {

        String clientAccessToken = PropertiesReader.getProperty("application.clientAccessToken");

        if (clientAccessToken.length() < 1) {
            showHelp(clientAccessToken, ERROR_EXIT_CODE);
        }
        startChatBot(clientAccessToken);
        System.out.println("Good Bye .....");
    }


    private static void showHelp(String errorMessage, int exitCode) {
        if (errorMessage != null && errorMessage.length() > 0) {
            System.err.println(errorMessage);
            System.err.println();
        }
        System.out.println("Usage: APIKEY\n");
        System.out.println("APIKEY  Your unique application key");
        System.out.println("        See https://docs.api.ai/docs/key-concepts for details");
        System.exit(exitCode);
    }

    private static void startChatBot(String clientAccessToken) {

        AIConfiguration configuration = new AIConfiguration(clientAccessToken);
        AIDataService dataService = new AIDataService(configuration);
        String line = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                System.out.print(INPUT_PROMPT);
                line = reader.readLine();
                if (line.equalsIgnoreCase("end") | line.equalsIgnoreCase("stop") | line == null | line == "") {
                    System.exit(1);
                }
                try {
                    AIRequest request = new AIRequest(line);
                    AIResponse response = dataService.request(request);

                    if (response.getStatus().getCode() == 200) {
                        System.out.println(response.getResult().getFulfillment().getSpeech());
                    } else {
                        System.err.println(response.getStatus().getErrorDetails());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.print(INPUT_PROMPT);
                }
            } while (true);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
