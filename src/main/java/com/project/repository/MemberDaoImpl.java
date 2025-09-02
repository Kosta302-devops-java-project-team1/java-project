package main.java.com.project.repository;

import main.java.com.project.common.DBManager;
import main.java.com.project.dto.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDaoImpl implements MemberDao {

    @Override
    public int registerMember(Member member) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into members(email, password) values(?,?)";
        int result = 0;
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, member.getEmail());
            ps.setString(2, member.getPassword());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("등록 실패");
        } finally {
            DBManager.releaseConnection(con, ps);
        }
        return result;
    }

    public Member findByEmail(String email) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;
        String sql = "select * from members where email = ?";
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
                member = new Member(rs.getLong("member_id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("created_at"),
                        rs.getLong("balance"),
                        (rs.getInt("is_admin") != 0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("뭔가 문제가 생김");
        } finally {
            DBManager.releaseConnection(con, ps, rs);
        }
        return member;
    }

    @Override
    public Member login(Member member) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member login = null;
        String sql = "select * from members where email = ? and password = ?";
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, member.getEmail());
            ps.setString(2, member.getPassword());
            rs = ps.executeQuery();
            if(rs.next()){
                login = new Member(rs.getLong("member_id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("created_at"),
                        rs.getLong("balance"),
                        (rs.getInt("is_admin") != 0));
            }
        } finally {
            DBManager.releaseConnection(con, ps, rs);
        }
        return login;
    }
}
