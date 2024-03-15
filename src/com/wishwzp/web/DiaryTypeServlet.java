package com.wishwzp.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wishwzp.dao.DiaryDao;
import com.wishwzp.dao.DiaryTypeDao;
import com.wishwzp.model.DiaryType;
import com.wishwzp.util.DbUtil;
import com.wishwzp.util.StringUtil;

/**
 * Servlet implementation class DiaryTypeServlet
 */
public class DiaryTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	DiaryTypeDao diaryTypeDao=new DiaryTypeDao();
	DiaryDao diaryDao=new DiaryDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiaryTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if("list".equals(action)){
			diaryTypeList(request,response);
		}else if("preSave".equals(action)){
			diaryTypePreSave(request,response);
		}else if("save".equals(action)){
			diaryTypeSave(request,response);
		}else if("delete".equals(action)){
			diaryTypeDelete(request,response);
		}
	}
	
	private void diaryTypeList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Connection con=null;
		try{
			con=dbUtil.getCon();
			List<DiaryType> diaryTypeList=diaryTypeDao.diaryTypeList(con);
			request.setAttribute("diaryTypeList", diaryTypeList);
			request.setAttribute("mainPage", "diaryType/diaryTypeList.jsp");
			request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void diaryTypePreSave(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//用于识别是添加类别还是修改类别
		String diaryTypeId=request.getParameter("diaryTypeId");
		if(StringUtil.isNotEmpty(diaryTypeId)){
			Connection con=null;
			try{
				con=dbUtil.getCon();
				DiaryType diaryType=diaryTypeDao.diaryTypeShow(con,diaryTypeId);
				request.setAttribute("diaryType", diaryType);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("mainPage", "diaryType/diaryTypeSave.jsp");
		request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
	}
	
	
	private void diaryTypeSave(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String diaryTypeId=request.getParameter("diaryTypeId");
		String typeName=request.getParameter("typeName");
		
		DiaryType diaryType=new DiaryType(typeName);
		
		if(StringUtil.isNotEmpty(diaryTypeId)){
			diaryType.setDiaryTypeId(Integer.parseInt(diaryTypeId));
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int saveNum=0;
			//用于判断是添加还是修改
			if(StringUtil.isNotEmpty(diaryTypeId)){
				saveNum=diaryTypeDao.diaryTypeUpdate(con, diaryType);
			}else{
				saveNum=diaryTypeDao.diaryTypeAdd(con, diaryType);
			}
			
			if(saveNum>0){
				request.getRequestDispatcher("diaryType?action=list").forward(request, response);
			}else{
				request.setAttribute("diaryType", diaryType);
				request.setAttribute("error", "保存失败！");
				request.setAttribute("mainPage", "diaryType/diaryTypeSave.jsp");
				request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void diaryTypeDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String diaryTypeId=request.getParameter("diaryTypeId");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			//判断该日记类别下是否有日记
			if(diaryDao.existDiaryWithTypeId(con, diaryTypeId)){
				request.setAttribute("error", "日志类别下有日志，不能删除该类别！");
			}else{
				diaryTypeDao.diaryTypeDelete(con, diaryTypeId);
			}
			request.getRequestDispatcher("diaryType?action=list").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
