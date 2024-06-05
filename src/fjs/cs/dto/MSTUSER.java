package fjs.cs.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class MSTUSER implements Serializable{

	private static final long serialVersionUID = 1L;
	private int psnCd;
	private String userId;
	private String password;
	private String userName;
	private Timestamp deleteYMD;
	private Timestamp insertYMD;
	private int insertPSNCD;
	private Timestamp updateYMD;
	private int updatePSNCD;
	private String type;
	
	public MSTUSER() {
		super();
	}
	
	public MSTUSER(int psnCd, String userName) {
		super();
		this.psnCd = psnCd;
		this.userName = userName;
	}

	public int getPsnCd() {
		return psnCd;
	}

	public void setPsnCd(int psnCd) {
		this.psnCd = psnCd;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Timestamp getDeleteYMD() {
		return deleteYMD;
	}
	public void setDeleteYMD(Timestamp deleteYMD) {
		this.deleteYMD = deleteYMD;
	}
	public Timestamp getInsertYMD() {
		return insertYMD;
	}
	public void setInsertYMD(Timestamp insertYMD) {
		this.insertYMD = insertYMD;
	}
	public int getInsertPSNCD() {
		return insertPSNCD;
	}
	public void setInsertPSNCD(int insertPSNCD) {
		this.insertPSNCD = insertPSNCD;
	}
	public Timestamp getUpdateYMD() {
		return updateYMD;
	}
	public void setUpdateYMD(Timestamp updateYMD) {
		this.updateYMD = updateYMD;
	}
	public int getUpdatePSNCD() {
		return updatePSNCD;
	}
	public void setUpdatePSNCD(int updatePSNCD) {
		this.updatePSNCD = updatePSNCD;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}