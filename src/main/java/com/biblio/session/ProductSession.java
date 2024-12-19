package com.biblio.session;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/session/owner/product/set-info")
public class ProductSession extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonData = sb.toString();

        JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
        String productId = jsonObject.has("productId") ? jsonObject.get("productId").getAsString() : null;
        String action = jsonObject.has("productAction") ? jsonObject.get("productAction").getAsString() : null;

        HttpSession session = request.getSession();
        session.setAttribute("productId", productId);

        if (action != null) {
            if (action.equals("delete")) {
                session.setAttribute("productAction", action);
            }
        }

        response.setContentType("application/json");
        response.getWriter().write("{\"status\":\"success\"}");
    }
}