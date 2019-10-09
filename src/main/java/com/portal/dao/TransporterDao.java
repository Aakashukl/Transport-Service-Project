package com.portal.dao;

import java.util.List;

import com.portal.entity.Deals;
import com.portal.entity.Query;
import com.portal.entity.Transporter;
import com.portal.entity.Vehicle;

public interface TransporterDao {
	
	public void saveTransporterObj(Transporter transporterObj);
	
	public Transporter getTransporterObj(Transporter transporterObj);
	
	public void saveVehicleObj(Vehicle vehicleObj);

	public List<Transporter> getListOfALLTransporter();

	public Transporter getTransporterObjByID(int transporterId);

	public void updateTransportRecord(Transporter transporterObj);

	public void deleteTransportRecord(Transporter transporterObj);

	public List<Vehicle> getListOfALLVehicle();

	public Vehicle getVehicleObjById(int vehicleId);

	public void updateVehicleRecord(Vehicle vehicleObj);

	public void deleteVehicleRecord(Vehicle vehicleObj);

	public List<Deals> getDealsofTransporter(int transporterId);

	public List<Query> getCustomerAllQueryList(int transporterId);

	public void saveResponseOfTransporter(int queryId, String transporterResponce);

	public Vehicle getVehicleById(int vehicleId);
}
