package validator;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class FormValidator {

    private HashMap<String, String> errors = new HashMap<String, String>();

    private Date date;
    private String from;
    private String to;
    private float amount;

    public boolean validateForm(HttpServletRequest req) {

        errors.clear();

        try {
            //Date
            checkDate(req.getParameter("date"));

            //From currency
            from = req.getParameter("from");

            if (from == null || from.equals("")) {
                errors.put("from-error", "Please select a currency!");
            }

            //To currency
            to = req.getParameter("to");

            if (to == null || to.equals("")) {
                errors.put("to-error", "Please select a currency!");
            }

            //Amount
            String paramAmount = req.getParameter("amount");

            if (paramAmount == null || paramAmount.equals("")) {
                errors.put("amount-error", "Amount cannot be blank!");
            } else {
                paramAmount = paramAmount.replace(',', '.');
                amount = Float.parseFloat(paramAmount);
                if (amount < 0 ) {
                    errors.put("amount-error", "Please enter only positive numbers!");
                }
            }

            if (errors.size() == 0) {
                return true;
            }
        } catch (NullPointerException e) {
            System.err.println("Validation error -> NullPointerException");
        } catch (NumberFormatException ex) {
            errors.put("amount-error", "Invalid amount format!");
        }
        return false;

    }

    public boolean validateDate(HttpServletRequest req) {

        errors.clear();
        return checkDate(req.getParameter("date"));

    }

    private boolean checkDate(String paramDate) {

        if (paramDate == null || paramDate.equals("")) {
            errors.put("date-error", "Date cannot be blank!");
            return false;
        }

        try {

            SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy");
            date = parser.parse(paramDate);

            Date dateNow = new Date();
            Calendar calendarNow = GregorianCalendar.getInstance();
            calendarNow.setTime(dateNow);

            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(date);

            if (date.compareTo(dateNow) > 0) {
                errors.put("date-error", "Date cannot be in future!");
            } else if (isSameDay(calendarNow, calendar) &&calendarNow.get(Calendar.HOUR_OF_DAY) < 13) {
                errors.put("date-error", "Exchange rates for today will be available after 13:00!");
            }

            if (errors.size() == 0) {
                return true;
            }
        } catch (ParseException e) {
            errors.put("date-error", "Invalid date format!");
        } catch (NullPointerException ex) {
            System.err.println("Validation error -> NullPointerException");
        }
        return false;
    }

    private boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            System.err.println("The dates must not be null");
            return false;
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public Date getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public float getAmount() {
        return amount;
    }

}
