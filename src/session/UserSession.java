package session;

import model.User;

public class UserSession {

    private static int userId;
    private static String username;
    private static String role;

    public static void setUser(User user){
        userId = user.getId();
        username = user.getUsername();
        role = user.getRole();
    }

    public static boolean isLogin(){
        return username != null && !username.isEmpty();
    }

    public static int getUserId(){
        return userId;
    }

    public static String getUsername(){
        return username;
    }

    public static String getRole(){
        return role;
    }

    public static void logout(){
        userId = 0;
        username = null;
        role = null;
    }
}