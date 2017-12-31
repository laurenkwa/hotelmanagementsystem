
/**
 * TestDriver.
 *
 * @author Lauren Kwa
 * @version 2017
 */
public class TestDriver {

    /**
     * Drives the program.
     * @param args unused
     * 
     */
    public static void main(String[] args) {
        
        //change username and password to credentials
        HotelSystemV1 hotel = new HotelSystemV1("jdbc:mysql://104.236.168.12:3306/testhotel",
                "username", "password");
        
        
        
        //hotel.checkoutGuest(1);
        
        //hotel.checkoutGuest(3);
        
        
        System.out.println("\nOccupied Rooms: \n" + hotel.getOccupiedRooms());
        System.out.println("\nVacant Rooms: \n" + hotel.getVacantRooms());
        
        System.out.println("\nOccupied rooms: " + hotel.getOccupiedRoomCount());
        
        System.out.println(hotel.checkRoomStatus(3));

    }

}
