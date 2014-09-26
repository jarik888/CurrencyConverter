package controller;

import com.google.gson.Gson;
import service.CurrencyService;
import validator.FormValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CurrencyService")
public class FrontController extends HttpServlet {

    private CurrencyService cService;
    private FormValidator fValidator;

    public void init(ServletConfig config) throws ServletException {
        cService = new CurrencyService();
        fValidator = new FormValidator();
    }

    Gson gson = new Gson();

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=utf-8");

        String operation = req.getParameter("op") == null ? "" : req.getParameter("op");

        if (operation.equals("exchange")) {

            if (fValidator.validateForm(req)) {

                res.getWriter().print(gson.toJson(cService.exchangeCurrency(fValidator.getDate(), fValidator.getFrom(), fValidator.getTo(), fValidator.getAmount())));

            } else {
                System.out.println(fValidator.getErrors());
                res.getWriter().print(gson.toJson(fValidator.getErrors()));
            }

        } else if (operation.equals("getCurrencies")) {

            if (fValidator.validateDate(req)) {

                res.getWriter().print(gson.toJson(cService.getBankCurrencies(fValidator.getDate())));

            } else {

                System.out.println(fValidator.getErrors());
                res.getWriter().print(gson.toJson(fValidator.getErrors()));

            }

        } else {
            req.getRequestDispatcher("view/index.jsp").forward(req,res);
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
}