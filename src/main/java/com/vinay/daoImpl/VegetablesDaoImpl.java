package com.vinay.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vinay.dao.VegetablesDao;
import com.vinay.model.VegetablesDetails;
import com.vinay.util.DBUtil;

public class VegetablesDaoImpl implements VegetablesDao{
	
private Connection connection;
public VegetablesDaoImpl() {
		this.connection = DBUtil.getConnection();
	}


//--------------------------------------------------------------------------------------//
	//--------------------------------------------------------------------------------------//
 /*------------------CLOSE COnnection When All DAO Operations are done ----------------*/	
 //--------------------------------------------------------------------------------------//	
	//--------------------------------------------------------------------------------------//
public void closeConnection() {
      if (connection != null) {
          try {
              connection.close();
              System.out.println("Database connection closed For Admin DAO");
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  }

//--------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------//




//To upload a vegetable we will do => checkVegetableExistsByVendor()  & then saveVegetable()
/*-PART 1----------------------- FIND VEGETABLE BY VENDOR ID AND VEGETBALE NAME --------------------------------*/

@Override
public Optional<VegetablesDetails> checkVegetableExistsByVendor(int vendor_id, String veg_name) {
	// TODO Auto-generated method stub
	
	String query = "SELECT * FROM vegetables_details WHERE vendor_id = ? AND veg_name = ?";
	ResultSet rs = null;
	try (PreparedStatement ps = connection.prepareStatement(query)) {
		ps.setInt(1,vendor_id);
		ps.setString(2, veg_name);
	 rs = ps.executeQuery();
		if (rs.next()) {
			VegetablesDetails details = new VegetablesDetails();
			details.setVegId(rs.getInt("veg_id"));
			details.setVendorId(rs.getInt("vendor_id"));
			details.setVegCategory(rs.getString("veg_category"));
			details.setVegPicName(rs.getString("veg_pic_name"));
			details.setVegName(rs.getString("veg_name"));
			details.setCreatedAt(rs.getTimestamp("created_at"));
			details.setDescription(rs.getString("description"));
			details.setPricePerPiece(rs.getDouble("price_per_piece"));
			details.setQuantity(rs.getInt("quantity"));
			details.setUpdatedAt(rs.getTimestamp("updated_at"));
				return Optional.of(details);
		}
		
	} catch (SQLException e) {
        e.printStackTrace();
    }
    finally {
		if(rs!=null) {try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}
    return Optional.empty();
}



/*-PART 2 ---------------------------INSERT(ADD) VEGETBALE INTO DATABASE --------------------------------------*/

@Override
public void saveVegetable(VegetablesDetails details) {
	
	
	
	
	  String query = "INSERT INTO vegetables_details (vendor_id, veg_name , quantity , description , price_per_piece , veg_pic_name, veg_category , discount_per_piece , net_price,is_active ) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)";
      try (PreparedStatement ps = connection.prepareStatement(query)) {
          ps.setInt(1, details.getVendorId());
          ps.setString(2, details.getVegName());
          ps.setInt(3, details.getQuantity());
          ps.setString(4,details.getDescription()); 
          ps.setDouble(5,details.getPricePerPiece() );
          ps.setString(6, details.getVegPicName());
          ps.setString(7, details.getVegCategory());
          ps.setDouble(8, details.getDiscount_per_piece());
          ps.setDouble(9, details.getNet_price());
          ps.setString(10,"active"); 
         //net_price
          ps.executeUpdate();
          System.out.print("SAVED VEGETABLE IN DATABASE ");
      } catch (SQLException e) {
          e.printStackTrace();
      }

	
}



/*----------------------------FETCH ALL  VEGETBALE FROM  DATABASE --------------------------------------*/
 
@Override
	public List<VegetablesDetails> getAllVegetablesByCategory(String Category) {
	
	 List<VegetablesDetails> vegetablesList = new ArrayList<VegetablesDetails>();
	 VegetablesDetails details  =  null;
     String query = "SELECT * FROM vegetables_details WHERE veg_category= ? AND is_active='active'";  // SQL query to fetch all vegetables
     ResultSet rs = null;
     try (PreparedStatement ps = connection.prepareStatement(query)) {
    	 ps.setString(1, Category);
          rs = ps.executeQuery();
         while (rs.next()) {
        	 details = new VegetablesDetails();
             details.setVegId(rs.getInt(1));
             details.setVendorId(rs.getInt(4));
             details.setVegName(rs.getString(5));
             details.setQuantity(rs.getInt(6));
             details.setDescription(rs.getString(7));
             details.setPricePerPiece(rs.getDouble(8));
             details.setVegPicName(rs.getString(9));
             details.setVegCategory(rs.getString(10));
             details.setDiscount_per_piece(rs.getDouble(11));
             details.setNet_price(rs.getDouble(12));
             details.setCreatedAt(rs.getTimestamp(2));
             details.setUpdatedAt(rs.getTimestamp(3));

             vegetablesList.add(details);  // Add the details to the list
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     finally {
			if(rs!=null) {try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}

     return vegetablesList;  // Return the list of vegetables
 }







@Override
	public List<VegetablesDetails> getAllVegetablesByName(String name) {
	
	 List<VegetablesDetails> vegetablesList = new ArrayList<VegetablesDetails>();
	 VegetablesDetails details  =  null;
     String query = "SELECT * FROM vegetables_details WHERE veg_name LIKE ? AND is_active='active'";  // SQL query to fetch all vegetables
     ResultSet rs = null;
     try (PreparedStatement ps = connection.prepareStatement(query)) {
    	 ps.setString(1, "%"+name+"%");
         rs = ps.executeQuery();
         while (rs.next()) {
        	 details = new VegetablesDetails();
             details.setVegId(rs.getInt(1));
             details.setVendorId(rs.getInt(4));
             details.setVegName(rs.getString(5));
             details.setQuantity(rs.getInt(6));
             details.setDescription(rs.getString(7));
             details.setPricePerPiece(rs.getDouble(8));
             details.setVegPicName(rs.getString(9));
             details.setVegCategory(rs.getString(10));
             details.setDiscount_per_piece(rs.getDouble(11));
             details.setNet_price(rs.getDouble(12));
             details.setCreatedAt(rs.getTimestamp(2));
             details.setUpdatedAt(rs.getTimestamp(3));

             vegetablesList.add(details);  // Add the details to the list
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     finally {
			if(rs!=null) {try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}

     return vegetablesList;  // Return the list of vegetables
 }




/*----------------------------FETCH ALL VEGETBALE FOR A PARTICULAR  VENDOR FROM DATABASE --------------------------------------*/

//WE WILL FETCH VEEGTABLES UPLOADED BY PARTICULAR VENDOR 
@Override
public List<VegetablesDetails> getAllVegetablesByVendorId(int vendor_id) {
	
	 List<VegetablesDetails> vegetablesList = new ArrayList<VegetablesDetails>();
	 VegetablesDetails details  =  null;
    String query = "SELECT * FROM vegetables_details WHERE vendor_id = ? AND is_active ='active'";  // SQL query to fetch all vegetables by a vendor id
    ResultSet rs = null;
    try (PreparedStatement ps = connection.prepareStatement(query)) {
    	ps.setInt(1,vendor_id);
         rs = ps.executeQuery();
        while (rs.next()) {
       	 details = new VegetablesDetails();
            details.setVegId(rs.getInt(1));
            details.setVendorId(rs.getInt(4));
            details.setVegName(rs.getString(5));
            details.setQuantity(rs.getInt(6));
            details.setDescription(rs.getString(7));
            details.setPricePerPiece(rs.getDouble(8));
            details.setVegPicName(rs.getString(9));
            details.setVegCategory(rs.getString(10));
            details.setDiscount_per_piece(rs.getDouble(11));
            details.setNet_price(rs.getDouble(12));
            details.setCreatedAt(rs.getTimestamp(2));
            details.setUpdatedAt(rs.getTimestamp(3));

            vegetablesList.add(details);  // Add the details to the list
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    finally {
		if(rs!=null) {try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}

    return vegetablesList;  // Return the list of vegetables
}

@Override
public List<VegetablesDetails> getAllDeletedVegetablesByVendorId(int vendor_id) {
	
	 List<VegetablesDetails> vegetablesList = new ArrayList<VegetablesDetails>();
	 VegetablesDetails details  =  null;
    String query = "SELECT * FROM vegetables_details WHERE vendor_id = ? AND is_active ='inactive'";  // SQL query to fetch all vegetables by a vendor id
    ResultSet rs = null;
    try (PreparedStatement ps = connection.prepareStatement(query)) {
    	ps.setInt(1,vendor_id);
         rs = ps.executeQuery();
        while (rs.next()) {
       	 details = new VegetablesDetails();
            details.setVegId(rs.getInt(1));
            details.setVendorId(rs.getInt(4));
            details.setVegName(rs.getString(5));
            details.setQuantity(rs.getInt(6));
            details.setDescription(rs.getString(7));
            details.setPricePerPiece(rs.getDouble(8));
            details.setVegPicName(rs.getString(9));
            details.setVegCategory(rs.getString(10));
            details.setDiscount_per_piece(rs.getDouble(11));
            details.setNet_price(rs.getDouble(12));
            details.setCreatedAt(rs.getTimestamp(2));
            details.setUpdatedAt(rs.getTimestamp(3));

            vegetablesList.add(details);  // Add the details to the list
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    finally {
		if(rs!=null) {try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}

    return vegetablesList;  // Return the list of vegetables
}

/*----------------------------FETCH IN STOCK  VEGETBALE FOR A PARTICULAR  VENDOR FROM DATABASE --------------------------------------*/


@Override
	public List<VegetablesDetails> getAllInStockVegetablesByVendorId(int vendor_id) {
	 List<VegetablesDetails> vegetablesList = new ArrayList<VegetablesDetails>();
	 VegetablesDetails details  =  null;
    String query = "SELECT * FROM vegetables_details WHERE vendor_id = ? AND quantity > 0 ";  // SQL query to fetch all vegetables by a vendor id
    ResultSet rs = null;
    try (PreparedStatement ps = connection.prepareStatement(query)) {
    	ps.setInt(1,vendor_id);
         rs = ps.executeQuery();
        while (rs.next()) {
       	 details = new VegetablesDetails();
            details.setVegId(rs.getInt(1));
            details.setVendorId(rs.getInt(4));
            details.setVegName(rs.getString(5));
            details.setQuantity(rs.getInt(6));
            details.setDescription(rs.getString(7));
            details.setPricePerPiece(rs.getDouble(8));
            details.setVegPicName(rs.getString(9));
            details.setVegCategory(rs.getString(10));
            details.setDiscount_per_piece(rs.getDouble(11));
            details.setNet_price(rs.getDouble(12));
            details.setCreatedAt(rs.getTimestamp(2));
            details.setUpdatedAt(rs.getTimestamp(3));

            vegetablesList.add(details);  // Add the details to the list
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    finally {
		if(rs!=null) {try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}

    return vegetablesList;  // Return the list of vegetables
	}



/*----------------------------FETCH OUT OF STOCK  VEGETBALE FOR A PARTICULAR  VENDOR FROM DATABASE --------------------------------------*/

@Override
	public List<VegetablesDetails> getAllOutOfStockVegetablesByVendorId(int vendor_id) {
	 List<VegetablesDetails> vegetablesList = new ArrayList<VegetablesDetails>();
	 VegetablesDetails details  =  null;
    String query = "SELECT * FROM vegetables_details WHERE vendor_id = ? AND quantity =0";  // SQL query to fetch all vegetables by a vendor id
    ResultSet rs = null;
    try (PreparedStatement ps = connection.prepareStatement(query)) {
    	ps.setInt(1,vendor_id);
         rs = ps.executeQuery();
        while (rs.next()) {
       	 details = new VegetablesDetails();
            details.setVegId(rs.getInt(1));
            details.setVendorId(rs.getInt(4));
            details.setVegName(rs.getString(5));
            details.setQuantity(rs.getInt(6));
            details.setDescription(rs.getString(7));
            details.setPricePerPiece(rs.getDouble(8));
            details.setVegPicName(rs.getString(9));
            details.setVegCategory(rs.getString(10));
            details.setDiscount_per_piece(rs.getDouble(11));
            details.setNet_price(rs.getDouble(12));
            details.setCreatedAt(rs.getTimestamp(2));
            details.setUpdatedAt(rs.getTimestamp(3));

            vegetablesList.add(details);  // Add the details to the list
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    finally {
		if(rs!=null) {try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}

    return vegetablesList;  // Return the list of vegetables
	}
	


/* ----------------------------FTECH A VEGETBALE INFO BY VEG ID--------------------------- */


@Override
public Optional<VegetablesDetails> getVegetableById(int vegId) {
    String query = "SELECT * FROM vegetables_details WHERE veg_id = ?";
    ResultSet rs = null;
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, vegId);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            VegetablesDetails details = new VegetablesDetails();
            details.setVegId(rs.getInt("veg_id"));
            details.setVendorId(rs.getInt("vendor_id"));
            details.setVegName(rs.getString("veg_name"));
            details.setQuantity(rs.getInt("quantity"));
            details.setDescription(rs.getString("description"));
            details.setPricePerPiece(rs.getDouble("price_per_piece"));
            details.setVegPicName(rs.getString("veg_pic_name"));
            details.setVegCategory(rs.getString("veg_category"));
            details.setDiscount_per_piece(rs.getDouble("discount_per_piece"));
            details.setNet_price(rs.getDouble("net_price"));
            details.setCreatedAt(rs.getTimestamp("created_at"));
            details.setUpdatedAt(rs.getTimestamp("updated_at"));
            
            return Optional.of(details);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    finally {
		if(rs!=null) {try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}
    
    return Optional.empty();
}


/*  ----------------------------------Anothet type */////////////////////////////////////////

public VegetablesDetails get_A_VegetableById(int vegId) throws SQLException {
    String query = "SELECT * FROM vegetables_details WHERE veg_id = ?";
    
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, vegId);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            VegetablesDetails details = new VegetablesDetails();
            details.setVegId(rs.getInt("veg_id"));
            details.setVendorId(rs.getInt("vendor_id"));
            details.setVegName(rs.getString("veg_name"));
            details.setQuantity(rs.getInt("quantity"));
            details.setDescription(rs.getString("description"));
            details.setPricePerPiece(rs.getDouble("price_per_piece"));
            details.setVegPicName(rs.getString("veg_pic_name"));
            details.setVegCategory(rs.getString("veg_category"));
            details.setDiscount_per_piece(rs.getDouble("discount_per_piece"));
            details.setNet_price(rs.getDouble("net_price"));
            details.setCreatedAt(rs.getTimestamp("created_at"));
            details.setUpdatedAt(rs.getTimestamp("updated_at"));
            
            return details;
        } else {
            throw new SQLException("Vegetable not found for ID: " + vegId);
        }
    }
}



/*------------------------------------GET ALL VENDOR NAMES -------------------------------*/


@Override
	public List<String> getAllVendorsNames() {
	 List<String> usernames = new ArrayList<>();
     String query = "SELECT DISTINCT u.username FROM users u JOIN vegetables_details v ON u.id = v.vendor_id";

     try (PreparedStatement ps =  connection.prepareStatement(query); 
   		  ResultSet rs = ps.executeQuery();)  {
       

         // Iterate through the result set and add usernames to the list
         while (rs.next()) {
             usernames.add(rs.getString("username"));
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     

     return usernames;  // Return the list of usernames
	
	
	
	}




/*----------GET Vendor Name Based on VEg id to know which vendor is selling which vegtable */

public String getUsernameByVegId(int vegId) {
    String username = null;
    String query = "SELECT u.username FROM users u JOIN vegetables_details v ON u.id = v.vendor_id WHERE v.veg_id = ?";
    ResultSet rs = null;
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, vegId);  // Set the veg_id parameter in the query
         rs = ps.executeQuery();

        if (rs.next()) {
            // Retrieve the username from the result set
            username = rs.getString("username");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    finally {
		if(rs!=null) {try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}

    return username;  // Return the username or null if not found
}


/*---------------------------------FETCH VEGETBALE DETAILS BY CART ID ---------------------------------*/

public VegetablesDetails getVegetableDetailsByCartId(int cartId) {
    String query = "SELECT v.veg_id, v.created_at, v.updated_at, v.vendor_id, v.veg_name, v.quantity, "
            + "v.description, v.price_per_piece, v.discount_per_piece, v.net_price, v.veg_pic_name, v.veg_category "
            + "FROM carts c "
            + "JOIN vegetables_details v ON c.veg_id = v.veg_id "
            + "WHERE c.cart_id = ?";

    VegetablesDetails vegetableDetails = null;

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, cartId);  // Set cart_id as a parameter

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                // Create VegetablesDetails object and populate it with data from ResultSet
                vegetableDetails = new VegetablesDetails();
                vegetableDetails.setVegId(rs.getInt("veg_id"));
                vegetableDetails.setCreatedAt(rs.getTimestamp("created_at"));
                vegetableDetails.setUpdatedAt(rs.getTimestamp("updated_at"));
                vegetableDetails.setVendorId(rs.getInt("vendor_id"));
                vegetableDetails.setVegName(rs.getString("veg_name"));
                vegetableDetails.setQuantity(rs.getInt("quantity"));
                vegetableDetails.setDescription(rs.getString("description"));
                vegetableDetails.setPricePerPiece(rs.getDouble("price_per_piece"));
                vegetableDetails.setDiscount_per_piece(rs.getDouble("discount_per_piece"));
                vegetableDetails.setNet_price(rs.getDouble("net_price"));
                vegetableDetails.setVegPicName(rs.getString("veg_pic_name"));
                vegetableDetails.setVegCategory(rs.getString("veg_category"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return vegetableDetails;  // Return the VegetablesDetails object
}




public List<VegetablesDetails> getVegetablesDetailsByOrderId(String orderId)  {
    List<VegetablesDetails> vegetableList = new ArrayList<>();

    // Query to join the orders, carts, and vegetables_details tables
    String query = "SELECT v.veg_id, v.created_at, v.updated_at, v.vendor_id, v.veg_name, " +
                   "v.quantity, v.description, v.price_per_piece, v.discount_per_piece, v.net_price, " +
                   "v.veg_pic_name, v.veg_category " +
                   "FROM orders o " +
                   "JOIN carts c ON o.cart_id = c.cart_id " +
                   "JOIN vegetables_details v ON c.veg_id = v.veg_id " +
                   "WHERE o.order_id = ?";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setString(1, orderId);  // Set the order_id as a parameter

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                VegetablesDetails vegDetail = new VegetablesDetails();

                // Populate the VegetablesDetails object from the result set
                vegDetail.setVegId(rs.getInt("veg_id"));
                vegDetail.setCreatedAt(rs.getTimestamp("created_at"));
                vegDetail.setUpdatedAt(rs.getTimestamp("updated_at"));
                vegDetail.setVendorId(rs.getInt("vendor_id"));
                vegDetail.setVegName(rs.getString("veg_name"));
                vegDetail.setQuantity(rs.getInt("quantity"));
                vegDetail.setDescription(rs.getString("description"));
                vegDetail.setPricePerPiece(rs.getDouble("price_per_piece"));
                vegDetail.setDiscount_per_piece(rs.getDouble("discount_per_piece"));
                vegDetail.setNet_price(rs.getDouble("net_price"));
                vegDetail.setVegPicName(rs.getString("veg_pic_name"));
                vegDetail.setVegCategory(rs.getString("veg_category"));

                // Add the populated VegetablesDetails object to the list
                vegetableList.add(vegDetail);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return vegetableList;  // Return the list of VegetablesDetails
}





public boolean deleteVegetableById(int vegId) {
	String sql = "UPDATE vegetables_details SET is_active = 'inactive' WHERE veg_id = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, vegId);
        int rowsUpdated = preparedStatement.executeUpdate();
        return rowsUpdated > 0; // Return true if the update was successful
    } catch (SQLException e) {
        e.printStackTrace(); // Handle exceptions appropriately
        return false; // Return false if there was an error
    }
}


// restore the vegetable back 
@Override
	public boolean restoreVegetableById(int vegId) {
	String sql = "UPDATE vegetables_details SET is_active = 'active' WHERE veg_id = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, vegId);
        int rowsUpdated = preparedStatement.executeUpdate();
        return rowsUpdated > 0; // Return true if the update was successful
    } catch (SQLException e) {
        e.printStackTrace(); // Handle exceptions appropriately
        return false; // Return false if there was an error
    }
	}


@Override
public void updateVegetable(VegetablesDetails details) {
    String query = "UPDATE vegetables_details SET vendor_id = ?, veg_name = ?, quantity = ?, description = ?, price_per_piece = ?, veg_pic_name = ?, veg_category = ?, discount_per_piece = ?, net_price = ? WHERE veg_id = ?";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, details.getVendorId());
        ps.setString(2, details.getVegName());
        ps.setInt(3, details.getQuantity());
        ps.setString(4, details.getDescription());
        ps.setDouble(5, details.getPricePerPiece());
        ps.setString(6, details.getVegPicName());
        ps.setString(7, details.getVegCategory());
        ps.setDouble(8, details.getDiscount_per_piece());
        ps.setDouble(9, details.getNet_price());
        ps.setInt(10, details.getVegId()); // Set veg_id for the update condition
        ps.executeUpdate();
        System.out.print("UPDATED VEGETABLE IN DATABASE ");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}





}
