package com.espark.adarsh.apiai;

/***********************************************************************************************************************
 *
 * API.AI Java SDK - client-side libraries for API.AI
 * =================================================
 *
 * Copyright (C) 2017 by Speaktoit, Inc. (https://www.speaktoit.com)
 * https://www.api.ai
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************/

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ai.api.AIServiceException;
import ai.api.model.AIResponse;
import ai.api.web.AIServiceServlet;


@WebServlet(
        name = "ServiceServletSample"
        , description = "A sample annotated servlet"
        , urlPatterns = {"/ai"}
        , initParams = {
        @WebInitParam(name = ServiceServletSample.PARAM_API_AI_KEY
                , value = "17f1d630ba2544bd8d041f0e2c0f3598")
}
)
public class ServiceServletSample extends AIServiceServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AIResponse aiResponse = request(request.getParameter("query"), request.getSession());
            response.setContentType("text/plain");
            response.getWriter().append(aiResponse.getResult().getFulfillment().getSpeech());
        } catch (AIServiceException e) {
            e.printStackTrace();
        }
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
