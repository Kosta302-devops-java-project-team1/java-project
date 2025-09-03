package main.java.com.project.repository;

import main.java.com.project.common.DBManager;
import main.java.com.project.dto.Member;
import main.java.com.project.exception.MemberNotFoundException;

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
            throw new SQLException("db오류");
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
            throw new SQLException("db오류");
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

    @Override
    public Member updatePassword(Member member, String password) throws MemberNotFoundException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        Member updated = null;
        String sql = "update members set password = ? where member_id = ?";

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setLong(2, member.getId());
            int result = ps.executeUpdate();
            if(result == 0){
                throw new MemberNotFoundException("로그인 상태가 아닌듯..?");
            }
            updated = findByEmail(member.getEmail());
        } catch (SQLException e) {
            throw new SQLException("db오류");
        } finally {
            DBManager.releaseConnection(con, ps);
        }
        return updated;
    }
}
