package com.example.demo.Utility;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.*;


public class ItemClassIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return session.doReturningWork(connection -> {
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery("SELECT MAX(ITEM_CLASS_ID) FROM ph_item_classes");

                if (rs.next()) {
                    int maxId = rs.getInt(1);
                    return maxId + 1;
                } else {
                    return 1;
                }
            } catch (Exception e) {
                throw new HibernateException("Unable to generate Sequence", e);
            }
        });
    }


//    @Override
//    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
//        return session.doReturningWork(connection -> {
//            try {
//                return findFirstUnusedId(connection);
//            } catch (SQLException e) {
//                throw new HibernateException("Unable to generate ID", e);
//            }
//        });
//    }
//
//    private Integer findFirstUnusedId(Connection connection) throws SQLException {
//        // Query to find the first unused ID
//        String query = "WITH RECURSIVE ids AS ( " +
//                "    SELECT 1 AS id " +
//                "    UNION ALL " +
//                "    SELECT id + 1 FROM ids " +
//                "    WHERE NOT EXISTS (SELECT 1 FROM ph_item_classes WHERE item_class_id = ids.id) " +
//                ") " +
//                "SELECT id FROM ids LIMIT 1";
//
//        try (PreparedStatement stmt = connection.prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//
//            if (rs.next()) {
//                return rs.getInt(1);
//            } else {
//                // If no unused ID is found, return the next available ID (max ID + 1)
//                return getNextAvailableId(connection);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private Integer getNextAvailableId(Connection connection) throws SQLException {
//        String query = "SELECT COALESCE(MAX(item_class_id), 0) + 1 FROM ph_item_classes";
//        try (PreparedStatement stmt = connection.prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//
//            if (rs.next()) {
//                return rs.getInt(1);
//            } else {
//                throw new SQLException("Unable to determine the next available ID");
//            }
//        }
//    }
}
