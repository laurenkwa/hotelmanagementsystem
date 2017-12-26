
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
        
        HotelSystemV1 hotel = new HotelSystemV1("jdbc:mysql://localhost:3306/testhotel",
                "root", "password");
        
        hotel.bookGuest("Test1", 4, 3);
        
        hotel.checkoutGuest(3);
        
        
        System.out.println("\nOccupied Rooms: \n" + hotel.getOccupiedRooms());
        System.out.println("\nVacant Rooms: \n" + hotel.getVacantRooms());
        
        System.out.println("\nOccupied rooms: " + hotel.getOccupiedRoomCount());
        
        System.out.println(hotel.checkRoomStatus(3));

    }

}
