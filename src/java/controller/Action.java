/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author 12530833630
 */
public interface Action {
    
    void execute(HttpServletRequest request,
                 HttpServletResponse response) throws IOException, ServletException;
    
}
