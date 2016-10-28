package com.example.vsaik.sjsumap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vsaik on 10/27/2016.
 */
public class AddressData {
    public static final double angle = 38.5;
    public List<String> addressList = new ArrayList<String>();

    public AddressData(){
        addressList.add("Dr. Martin Luther King,Jr. Library,150 East San Fernando Street,San Jose, CA 95112");
        addressList.add("San José State University Charles W. Davidson College of Engineering, 1 Washington Square, San Jose, CA 95112");
        addressList.add("Yoshihiro Uchida Hall, San Jose, CA 95112");
        addressList.add("Student Union Building, San Jose, CA 95112");
        addressList.add("Boccardo Business Complex, San Jose, CA 95112");
        addressList.add("San Jose State University South Garage, 330 South 7th Street, San Jose, CA 95112");
    }

    public List<String> getAddressList(){
        return addressList;
    }
}
