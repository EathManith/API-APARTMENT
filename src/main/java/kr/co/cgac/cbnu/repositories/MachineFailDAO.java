package kr.co.cgac.cbnu.repositories;

import kr.co.cgac.cbnu.configuration.DBUtility;
import kr.co.cgac.cbnu.models.MachineFail;
import kr.co.cgac.cbnu.utilities.Pagination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgac_315 on 6/16/2017.
 */
public class MachineFailDAO {

    private Connection cnn = null;

    public List<MachineFail> getAllMachineFails(Pagination pagination) {
        pagination.setTotalCount(count(pagination));
        List<MachineFail> machineFailList = new ArrayList<MachineFail>();
        String sql = "SELECT *" +
                     "FROM machine_failure_records " +
                     "ORDER BY id DESC " +
                     "LIMIT ? " +
                     "OFFSET ? ";
        cnn = DBUtility.getConnection();
        if (cnn != null) {
            try {
                PreparedStatement pstmt = cnn.prepareStatement(sql);
                pstmt.setInt(1, pagination.getLimit());
                pstmt.setInt(2, pagination.getOffset());
                ResultSet rs = pstmt.executeQuery();
                MachineFail machineFail = null;
                while (rs.next()) {
                    machineFail = new MachineFail(rs.getString("f_id"),
                                                rs.getString("f_line"),
                                                rs.getString("f_machine"),
                                                rs.getString("downtime"),
                                                rs.getString("restart_time"),
                                                rs.getString("f_code"),
                                                rs.getString("f_subcode"),
                                                rs.getString("f_detail"),
                                                rs.getString("f_phen"),
                                                rs.getString("repair_detail"),
                                                rs.getString("workers"),
                                                rs.getString("work_start"),
                                                rs.getString("work_end"),
                                                rs.getDouble("work_duration")
                    );
                    machineFailList.add(machineFail);
                }
                return machineFailList;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
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

    public MachineFail getMachineFails(String fId) {
        String sql = "SELECT *" +
                    "FROM machine_failure_records " +
                    "WHERE f_id = ?";
        cnn = DBUtility.getConnection();
        if (cnn != null) {
            try {
                PreparedStatement pstmt = cnn.prepareStatement(sql);
                pstmt.setString(1, fId);
                ResultSet rs = pstmt.executeQuery();
                MachineFail machineFail = null;
                while (rs.next()) {
                    machineFail = new MachineFail(rs.getString("f_id"),
                            rs.getString("f_line"),
                            rs.getString("f_machine"),
                            rs.getString("downtime"),
                            rs.getString("restart_time"),
                            rs.getString("f_code"),
                            rs.getString("f_subcode"),
                            rs.getString("f_detail"),
                            rs.getString("f_phen"),
                            rs.getString("repair_detail"),
                            rs.getString("workers"),
                            rs.getString("work_start"),
                            rs.getString("work_end"),
                            rs.getDouble("work_duration")
                    );
                }
                return machineFail;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
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

    public boolean deleteMachineFail(String fId) {
        String sql = "DELETE FROM machine_failure_records WHERE f_id = ?";
        cnn = DBUtility.getConnection();
        if (cnn != null) {
            try {
                PreparedStatement pstmt = cnn.prepareStatement(sql);
                pstmt.setString(1, fId);
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

    public boolean addMachineFail(MachineFail machineFail) {
        String sql = "INSERT INTO machine_failure_records VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        cnn = DBUtility.getConnection();
        if (cnn != null) {
            try {
                PreparedStatement pstmt = cnn.prepareStatement(sql);
                pstmt.setString(1, machineFail.getfId());
                pstmt.setString(2, machineFail.getfCode());
                pstmt.setString(3, machineFail.getfMachine());
                pstmt.setString(4, machineFail.getDownTime());
                pstmt.setString(5, machineFail.getRestartTime());
                pstmt.setString(6, machineFail.getfCode());
                pstmt.setString(7, machineFail.getfSubCode());
                System.out.println();
                pstmt.setString(8, machineFail.getfDetail());
                pstmt.setString(9, machineFail.getfPhen());
                pstmt.setString(10, machineFail.getRepairDetail());
                pstmt.setString(11, machineFail.getWorker());
                pstmt.setString(12, machineFail.getWorkStart());
                pstmt.setString(13, machineFail.getWorkEnd());
                pstmt.setDouble(14, machineFail.getWorkDuration());
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

    public boolean updateMachineFail(MachineFail machineFail) {
        String sql = "UPDATE machine_failure_records SET f_line = ?, f_machine = ?, downtime = ?, restart_time = ?, " +
                                                        "f_code = ?, f_subcode = ?, f_detail = ?, f_phen = ?, " +
                                                        "repair_detail = ?, workers = ?, work_start = ?, work_end = ?, work_duration = ? " +
                    "WHERE f_id = ?";
        cnn = DBUtility.getConnection();
        if (cnn != null) {
            try {
                PreparedStatement pstmt = cnn.prepareStatement(sql);
                pstmt.setString(1, machineFail.getfCode());
                pstmt.setString(2, machineFail.getfMachine());
                pstmt.setString(3, machineFail.getDownTime());
                pstmt.setString(4, machineFail.getRestartTime());
                pstmt.setString(5, machineFail.getfCode());
                pstmt.setString(6, machineFail.getfSubCode());
                pstmt.setString(7, machineFail.getfDetail());
                pstmt.setString(8, machineFail.getfPhen());
                pstmt.setString(9, machineFail.getRepairDetail());
                pstmt.setString(10, machineFail.getWorker());
                pstmt.setString(11, machineFail.getWorkStart());
                pstmt.setString(12, machineFail.getWorkEnd());
                pstmt.setDouble(13, machineFail.getWorkDuration());
                pstmt.setString(14, machineFail.getfId());
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

    private Long count(Pagination pagination) {
        String sql = "SELECT COUNT(1) AS total " +
                    "FROM machine_failure_records " +
                    "LIMIT ? " +
                    "OFFSET ?";
        cnn = DBUtility.getConnection();
        if (cnn != null) {
            try {
                PreparedStatement pstmt = cnn.prepareStatement(sql);
                pstmt.setInt(1, pagination.getLimit());
                pstmt.setInt(2, pagination.getOffset());
                ResultSet rs = pstmt.executeQuery();
                MachineFail machineFail = null;
                if(rs.next()) {
                    return rs.getLong("total");
                } else {
                    return 0L;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
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
}
