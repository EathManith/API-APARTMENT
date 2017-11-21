package kr.co.cgac.cbnu.repositories;

import kr.co.cgac.cbnu.configuration.DBUtility;
import kr.co.cgac.cbnu.models.Apartment;
import kr.co.cgac.cbnu.models.MachineFail;
import kr.co.cgac.cbnu.utilities.Pagination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApartmentDAO {
    private Connection cnn = null;

    /* List all apartments with pagination */
    public List<Apartment> getAllApartment(Pagination pagination, String name) {
        pagination.setTotalCount(count(name));
        List<Apartment> apartmentList = new ArrayList<Apartment>();
        String sql = "SELECT A.apart_id, A.apart_name, A.dong_id, A.gu_id, A.city_id, B.url_image " +
                "FROM tblLevel44Apartment A " +
                "LEFT JOIN tblApartment B ON A.apart_id = B.apart_id " +
                "WHERE A.apart_name LIKE ? LIMIT ? OFFSET ?";
        cnn = DBUtility.getConnection();
        if (cnn != null) {
            try {
                PreparedStatement pstmt = cnn.prepareStatement(sql);
                pstmt.setString(1, "%" + name + "%");
                pstmt.setInt(2, pagination.getLimit());
                pstmt.setInt(3, pagination.getOffset());
                ResultSet rs = pstmt.executeQuery();
                Apartment apartment = null;
                while (rs.next()) {
                    apartment = new Apartment();
                    apartment.setApart_id(rs.getString("apart_id"));
                    apartment.setApart_name(rs.getString("apart_name"));
                    apartment.setDong_id(rs.getLong("dong_id"));
                    apartment.setGu_id(rs.getLong("gu_id"));
                    apartment.setCity_id(rs.getLong("city_id"));
                    apartment.setUrl_image(rs.getString("url_image"));
                    apartmentList.add(apartment);
                }
                return apartmentList;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    cnn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No Connection!");
        }
        return null;
    }

    private Long count(String name) {
        String sql = "SELECT COUNT(1) AS total " +
                "FROM tblLevel44Apartment A " +
                "LEFT JOIN tblApartment B ON A.apart_id = B.apart_id " +
                "WHERE A.apart_name LIKE ?";
        cnn = DBUtility.getConnection();
        if (cnn != null) {
            try {
                PreparedStatement pstmt = cnn.prepareStatement(sql);
                pstmt.setString(1, "%" + name + "%");
                ResultSet rs = pstmt.executeQuery();
                MachineFail machineFail = null;
                if (rs.next()) {
                    return rs.getLong("total");
                } else {
                    return 0L;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    cnn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No Connection!");
        }
        return 0L;
    }

    /* List apartments by Id (dongId, guId, cityId) */
    public List<Apartment> getApartmentById(Long dongId, Long guId, Long cityId) {
        List<Apartment> apartmentByIdList = new ArrayList<Apartment>();
        String sql = "SELECT tb4.apart_id, tb4.apart_name, tb3.dong_id, tb3.gu_id, tb3.city_id FROM tblLevel44Apartment As tb4 INNER JOIN tblLevel33 As tb3 ON tb4.dong_id = tb3.dong_id AND tb4.gu_id = tb3.gu_id  AND tb4.city_id = tb3.city_id WHERE tb4.dong_id = ? AND tb4.gu_id = ? AND tb4.city_id = ?";
        cnn = DBUtility.getConnection();
        if (cnn != null) {
            try {
                PreparedStatement pstmt = cnn.prepareStatement(sql);
                pstmt.setLong(1, dongId);
                pstmt.setLong(2, guId);
                pstmt.setLong(3, cityId);
                ResultSet rs = pstmt.executeQuery();
                Apartment apart = null;
                while (rs.next()) {
                    apart = new Apartment();
                    apart.setApart_id(rs.getString("apart_id"));
                    apart.setApart_name(rs.getString("apart_name"));
                    apart.setGu_id(rs.getLong("dong_id"));
                    apart.setGu_name(rs.getString("gu_id"));
                    apart.setCity_id(rs.getLong("city_id"));
                    apartmentByIdList.add(apart);
                }
                return apartmentByIdList;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    cnn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No Connection!");
        }
        return null;
    }

    /* Get all cities */
    public List<Apartment> getCities() {
        List<Apartment> cityList = new ArrayList<Apartment>();
        String sql = "SELECT * FROM tblLevel11";
        cnn = DBUtility.getConnection();
        if (cnn != null) {
            try {
                PreparedStatement pstmt = cnn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                Apartment city = null;
                while (rs.next()) {
                    city = new Apartment();
                    city.setCity_id(rs.getLong("city_id"));
                    city.setCity_name(rs.getString("city_name"));
                    cityList.add(city);
                }
                return cityList;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    cnn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No Connection!");
        }
        return null;
    }

    /* Get gu by requesting one parameter cityId */
    public List<Apartment> getGu(Long cityId) {
        List<Apartment> guList = new ArrayList<Apartment>();
        String sql = "SELECT tb2.gu_id, tb2.gu_name, tb1.city_id FROM tblLevel22 As tb2 INNER JOIN tblLevel11 As tb1 ON tb2.city_id = tb1.city_id WHERE tb2.city_id = ?";
        cnn = DBUtility.getConnection();
        if (cnn != null) {
            try {
                PreparedStatement pstmt = cnn.prepareStatement(sql);
                pstmt.setLong(1, cityId);
                ResultSet rs = pstmt.executeQuery();
                Apartment gu = null;
                while (rs.next()) {
                    gu = new Apartment();
                    gu.setGu_id(rs.getLong("gu_id"));
                    gu.setGu_name(rs.getString("gu_name"));
                    gu.setCity_id(rs.getLong("city_id"));
                    guList.add(gu);
                }
                return guList;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    cnn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No Connection!");
        }
        return null;
    }

    /* Get dong by requesting two parameters guId and cityId */
    public List<Apartment> getDong(Long guId, Long cityId) {
        List<Apartment> dongList = new ArrayList<Apartment>();
        String sql = "SELECT tb3.dong_id, tb3.dong_name, tb2.gu_id, tb2.city_id FROM tblLevel33 As tb3 INNER JOIN tblLevel22 As tb2 ON tb3.gu_id = tb2.gu_id AND tb3.city_id = tb2.city_id WHERE tb3.gu_id = ? AND tb3.city_id = ? ";
        cnn = DBUtility.getConnection();
        if (cnn != null) {
            try {
                PreparedStatement pstmt = cnn.prepareStatement(sql);
                pstmt.setLong(1, guId);
                pstmt.setLong(2, cityId);
                ResultSet rs = pstmt.executeQuery();
                Apartment dong = null;
                while (rs.next()) {
                    dong = new Apartment();
                    dong.setDong_id(rs.getLong("dong_id"));
                    dong.setDong_name(rs.getString("dong_name"));
                    dong.setGu_id(rs.getLong("gu_id"));
                    dong.setCity_id(rs.getLong("city_id"));
                    dongList.add(dong);
                }
                return dongList;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    cnn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No Connection!");
        }
        return null;
    }

    public boolean deleteApartmentInfo(String apartId){
        String sql = "DELETE FROM tblApartment  WHERE apart_id = ?";
        cnn = DBUtility.getConnection();
        if (cnn != null) {
            try {
                PreparedStatement pstmt = cnn.prepareStatement(sql);
                pstmt.setString(1, apartId);
                if(pstmt.executeUpdate() > 0) {
                   return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No connection");
        }
        return false;
    }

    public boolean updateApartmentInfo(Apartment apartment) {
        String sql = "UPDATE tblApartment SET url_image = ? " +
                "WHERE apart_id = ?";
        cnn = DBUtility.getConnection();
        if (cnn != null) {
            try {
                PreparedStatement pstmt = cnn.prepareStatement(sql);
                pstmt.setString(1, apartment.getApart_id());
                pstmt.setString(2, apartment.getUrl_image());
                if(pstmt.executeUpdate() > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No connection");
        }
        return false;
    }


}
