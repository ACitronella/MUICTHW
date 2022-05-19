// country code      : iso country code, 2 characters
// postal code       : varchar(20)
// place name        : varchar(180)
// admin name1       : 1. order subdivision (state) varchar(100)
// admin code1       : 1. order subdivision (state) varchar(20)
// admin name2       : 2. order subdivision (county/province) varchar(100)
// admin code2       : 2. order subdivision (county/province) varchar(20)
// admin name3       : 3. order subdivision (community) varchar(100)
// admin code3       : 3. order subdivision (community) varchar(20)
// latitude          : estimated latitude (wgs84)
// longitude         : estimated longitude (wgs84)
// accuracy          : accuracy of lat/lng from 1=estimated, 4=geonameid, 6=centroid of addresses or shape

package src;

public class GeoName {
    String countryCode;
    String postalCode;
    String placeName;
    String adminName1;
    String adminCode1;
    String adminName2;
    String adminCode2;
    String adminName3;
    String adminCode3;
    double latitude;
    double longtitude;
    String accuracy;

    public GeoName(
        String countryCode,
        String postalCode,
        String placeName,
        String adminName1,
        String adminCode1,
        String adminName2,
        String adminCode2,
        String adminName3,
        String adminCode3,
        double latitude,
        double longtitude,
        String accuracy){
        
        this.countryCode = countryCode;
        this.postalCode = postalCode;
        this.placeName = placeName;
        this.adminName1 = adminName1;
        this.adminCode1 = adminCode1;
        this.adminName2 = adminName2;
        this.adminCode2 = adminCode2;
        this.adminName3 = adminName3;
        this.adminCode3 = adminCode3;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.accuracy = accuracy;

    }

    public String toString(){
        return String.format("countryCode=\"%s\", postalCode=\"%s\", placeName=\"%s\", adminName1=\"%s\", adminCode1=\"%s\", adminName2=\"%s\", adminCode2=\"%s\", adminName3=\"%s\", adminCode3=\"%s\", latitude=%.2f, longtitude=%.2f, accuracy=\"%s\"",
                countryCode,
                postalCode,
                placeName,
                adminName1,
                adminCode1,
                adminName2,
                adminCode2,
                adminName3,
                adminCode3,
                latitude,
                longtitude,
                accuracy);
    }

}