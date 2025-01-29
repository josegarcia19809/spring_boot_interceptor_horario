package com.example.interceptor_horario.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class CalendarInterceptor implements HandlerInterceptor {

    @Value("${config.calendar.open}")
    private Integer open;

    @Value("${config.calendar.close}")
    private Integer close;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
        if (hour >= open && hour <= close) {
            StringBuilder message = new StringBuilder();
            message.append("Bienvenidos al horario de atención al cliente");
            message.append(", atendemos desde las ");
            message.append(open);
            message.append("hrs.");
            message.append(" Hasta las ");
            message.append(close);
            message.append("hrs.");
            message.append("Gracias por su visita");
            request.setAttribute("message", message.toString());
            System.out.println(message.toString());
            return true;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> data = new HashMap<>();
        StringBuilder message = new StringBuilder();
        message.append("Cerrado, fuera del horario de atención");
        message.append(", por favor visitenos desde las ");
        message.append(open);
        message.append("hrs.");
        message.append(" y las ");
        message.append(close);
        message.append("hrs.");
        message.append("Gracias");
        data.put("message", message.toString());
        data.put("date", new Date());

        // Devuelve un response porque no pasa al controlador
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(data));

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

    }
}
