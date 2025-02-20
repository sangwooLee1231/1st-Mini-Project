package com.ssginc.login.model.dao;

import com.ssginc.login.model.dto.UsersDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO implements LoginDAOInterface {

    /**
     * 회원가입
     * @param con
     * @param user
     * @return
     */
    @Override
    public int insertUsers(Connection con, UsersDTO user) {
        int res = 0;

        String sql = "insert into users (users_id, users_pw, users_role, users_name, users_birth) values (?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getUsersId());
            ps.setString(2, user.getUsersPw());
            ps.setInt(3, user.getUsersRole());
            ps.setString(4, user.getUsersName());
            ps.setString(5, user.getUsersBirth());

            res = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }

    /**
     * 아이디 일치 여부 확인하는 메서드
     * @param id
     * @return
     */
    @Override
    public UsersDTO matchUsersId(Connection con, String id) {
        UsersDTO user = null;

        String sql = "select * from users where users_id = ? and is_active = 1 ";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, id);

            ResultSet rset = ps.executeQuery();

            if (rset.next()) {
                user = UsersDTO.builder()
                        .usersNo(rset.getInt("users_no"))
                        .usersId(rset.getString("users_id"))
                        .usersPw(rset.getString("users_pw"))
                        .usersRole(rset.getInt("users_role"))
                        .usersName(rset.getString("users_name"))
                        .build();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

}
