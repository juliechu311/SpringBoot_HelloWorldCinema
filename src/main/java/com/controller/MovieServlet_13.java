package com.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Movie;
import com.service.MovieService_13;

@WebServlet("/movie/movie.do")
public class MovieServlet_13 extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
			doPost(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
//	單一電影
	if("getOne_For_Info".equals(action)) {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		/*************************** 1.接收請求參數 **********************/
		Integer movieId = Integer.valueOf(req.getParameter("movieId"));

		/*************************** 2.開始查詢資料 ****************************************/
		MovieService_13 movieSvc = new MovieService_13();
		Movie movie = movieSvc.getOneMovie(movieId);

		/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
		req.setAttribute("movie", movie);
		String url = "/front_end/movie/singleMovie.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
	}
	
	
	
//	現正熱映
	if ("getNowMovies".equals(action)) {
        // 調用 getNowMovies 方法獲取狀態為「熱映中」的電影列表
        String movieStatus = "熱映中";
        MovieService_13 movieSvc = new MovieService_13();
        List<Movie> nowShowingMovies = movieSvc.getNowMovies(movieStatus);

        // 將狀態為「熱映中」的電影列表設置到 request 的屬性中
        req.setAttribute("nowShowingMovies", nowShowingMovies);

        // 轉發到相應的 JSP 文件中顯示結果
        String url = "/front_end/movie/movieNowShowing.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, res);
    }
	
//	即將上映
	if ("getSoonMovies".equals(action)) {
        // 調用 getNowMovies 方法獲取狀態為「即將上映」的電影列表
        String movieStatus = "即將上映";
        MovieService_13 movieSvc = new MovieService_13();
        List<Movie> CommingSoonMovies = movieSvc.getSoonMovies(movieStatus);

        // 將狀態為「熱映中」的電影列表設置到 request 的屬性中
        req.setAttribute("CommingSoonMovies", CommingSoonMovies);

        // 轉發到相應的 JSP 文件中顯示結果
        String url = "/front_end/movie/movieCommingSoon.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, res);
    }
		
		
	
	}
}
