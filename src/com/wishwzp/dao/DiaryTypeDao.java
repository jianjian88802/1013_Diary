package com.wishwzp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wishwzp.model.DiaryType;

public class DiaryTypeDao {

	/**
	 * 查询日记类型列表显示
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public List<DiaryType> diaryTypeCountList(Connection con)throws Exception{
		List<DiaryType> diaryTypeCountList=new ArrayList<DiaryType>();
		String sql="SELECT diaryTypeId,typeName,COUNT(diaryId) as diaryCount FROM t_diary RIGHT JOIN t_diaryType ON t_diary.typeId=t_diaryType.diaryTypeId GROUP BY typeName;";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			DiaryType diaryType=new DiaryType();
			diaryType.setDiaryTypeId(rs.getInt("diaryTypeId"));
			diaryType.setTypeName(rs.getString("typeName"));
			diaryType.setDiaryCount(rs.getInt("diaryCount"));
			diaryTypeCountList.add(diaryType);
		}
		return diaryTypeCountList;
	}
	
	/**
	 * 日志类别信息列表
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public List<DiaryType> diaryTypeList(Connection con)throws Exception{
		List<DiaryType> diaryTypeList=new ArrayList<DiaryType>();
		String sql="select * from t_diaryType";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			DiaryType diaryType=new DiaryType();
			diaryType.setDiaryTypeId(rs.getInt("diaryTypeId"));
			diaryType.setTypeName(rs.getString("typeName"));
			diaryTypeList.add(diaryType);
		}
		return diaryTypeList;
	}
	
	/**
	 * 日记类别列表显示
	 * @param con
	 * @param diaryTypeId
	 * @return
	 * @throws Exception
	 */
	public DiaryType diaryTypeShow(Connection con,String diaryTypeId)throws Exception{
		String sql="SELECT * from t_diaryType where diaryTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, diaryTypeId);
		ResultSet rs=pstmt.executeQuery();
		DiaryType diaryType=new DiaryType();
		if(rs.next()){
			diaryType.setDiaryTypeId(rs.getInt("diaryTypeId"));
			diaryType.setTypeName(rs.getString("typeName"));
		}
		return diaryType;
	}
	
	/**
	 * 添加日记类别
	 * @param con
	 * @param diaryType
	 * @return
	 * @throws Exception
	 */
	public int diaryTypeAdd(Connection con,DiaryType diaryType)throws Exception{
		String sql="insert into t_diaryType values(null,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, diaryType.getTypeName());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 更新日记类别
	 * @param con
	 * @param diaryType
	 * @return
	 * @throws Exception
	 */
	public int diaryTypeUpdate(Connection con,DiaryType diaryType)throws Exception{
		String sql="update t_diaryType set typeName=? where diaryTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, diaryType.getTypeName());
		pstmt.setInt(2, diaryType.getDiaryTypeId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 删除日记类别
	 * @param con
	 * @param diaryTypeId
	 * @return
	 * @throws Exception
	 */
	public int diaryTypeDelete(Connection con,String diaryTypeId)throws Exception{
		String sql="delete from t_diaryType where diaryTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, diaryTypeId);
		return pstmt.executeUpdate();
	}
	
}
