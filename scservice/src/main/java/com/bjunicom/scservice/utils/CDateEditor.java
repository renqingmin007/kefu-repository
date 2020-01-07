package com.bjunicom.scservice.utils;

import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.text.DateFormat;
import java.text.ParseException;

public class CDateEditor extends CustomDateEditor {

    private DateFormat dateFormat;
    private int exactDateLength = -1;
    private final boolean allowEmpty;

    public CDateEditor(DateFormat dateFormat, boolean allowEmpty) {
        super(dateFormat, allowEmpty);
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        /*
         * 判断是不是空串，此处也可以判断格式是否正确
         */
        if (text.replaceAll("\\s", "").length() == 0) {
            // Treat empty String as null value.
            setValue(null);
        } else if (text != null && this.exactDateLength >= 0
                && text.length() != this.exactDateLength) {
            throw new IllegalArgumentException(
                    "Could not parse date: it is not exactly"
                            + this.exactDateLength + "characters long");
        } else {
            try {
                setValue(this.dateFormat.parse(text));
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: "
                        + ex.getMessage(), ex);
            }
        }
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }

}