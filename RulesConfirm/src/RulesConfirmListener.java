import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
public class RulesConfirmListener extends PluginListener {
	private PropertiesFile properties = new PropertiesFile("plugins/config/RCprops.properties");
	String msg1;
	String msg2;
	String msg3;
	String msg4;
	String msg5;
	String msg6;
	String msg7;
	String file = "plugins/config/RCplayers.txt";
	File props = new File(file);
	String rule = "plugins/config/RCrules.txt";
	File rules = new File(rule);
	public void loadConfiguration() {
		msg1 = properties.getString("Main-Message","&2please do '/rules' to see the rules."); 
		msg2 = properties.getString("Accept-Message","&2Thank You for reading the rules and accepting them :D.");
		msg4 = properties.getString("Deny-Message","&4Youll have to accept the rules");
		msg3 = properties.getString("Error-Message","&4o.O an error occured /tell an admin to help you.");
		msg5 = properties.getString("File-Not-Found-Message","&4Sorry But We Could Not Find The Rules \n Please do /accept rules to continiue ");
		msg6 = properties.getString("Rules-Message","&2Please do &4/Acceptrules or &4/denyrules");
		msg7 = properties.getString("Already-Accepted-Message","&4You already accepted the rules.");
	}
	public boolean onItemUse(Player player,Block blockPlaced,Block blockClicked,Item itemInHand){
		if (!isInList(player.getName())){
			msg1 = msg1.replaceAll("&", "§");
			player.sendMessage(msg1);
			return true;
		}else{
		return false;
	}
	}
	
	public boolean onOpenInventory(Player player,Inventory inventory){
		if (!isInList(player.getName())){
			msg1 = msg1.replaceAll("&", "§");
			player.sendMessage(msg1);
			return true;
		}else{
		return false;
		}		
	}
	
	public void onPlayerMove(Player player, Location from, Location to)
    {
		if (!isInList(player.getName())){
        Location location = findPlaceToStand(player, "down");
        try {
          player.teleportTo(from.x, location.y, from.z, player.getRotation(), player.getPitch()); } catch (Throwable t) {
          player.teleportTo(from);
        }
      }
    }
	
	public boolean onChat(Player player,java.lang.String message){
		if (!isInList(player.getName())){
	            msg1 = msg1.replaceAll("&", "§");
			player.sendMessage(msg1);
	        return true;
		}else{
		return false;
	}
	}
	
	public boolean onBlockBreak(Player player,Block block){
		if (!isInList(player.getName())){
	            msg1 = msg1.replaceAll("&", "§");
			player.sendMessage(msg1);
			return true;
		}else{
		return false;
		}
	}
	
	public boolean onBlockCreate(Player player,Block blockPlaced,Block blockClicked,int itemInHand){
		if (!isInList(player.getName())){
	            msg1 = msg1.replaceAll("&", "§");
			player.sendMessage(msg1);
			return true;
		}else{
		return false;
	}
	}
	
	public void onLogin(Player player){
		if (!isInList(player.getName())){
	            msg1 = msg1.replaceAll("&", "§");
			player.sendMessage(msg1);
		}
	}
	public boolean onItemDrop(Player player,Item item){
		if (!isInList(player.getName())){
	            msg1 = msg1.replaceAll("&", "§");
			player.sendMessage(msg1);
			return true;
		}else{
		return false;
	}
	}
	public boolean onItemPickUp(Player player,Item item){
		if (!isInList(player.getName())){
	            msg1 = msg1.replaceAll("&", "§");
			player.sendMessage(msg1);
			return true;
		}else{
		return false;
		}
	}
	
	public boolean onCommand(Player player,java.lang.String[] split){
		if(split[0].equalsIgnoreCase("/acceptrules")){
			if (isInList(player.getName())){
				msg7 = msg7.replaceAll("&", "§");
				player.sendMessage(msg7);
				return true;
			}else{
			Writer writer = null;
            try {
           	 if (!new File(file).exists()) {
           		 props.createNewFile();
           		 writer = new FileWriter(file, true);
           		 writer.write(player.getName());
           		 return true;
           	 }else{
           		 writer = new FileWriter(file, true);
				writer.write(player.getName()+"\n");
				writer.close();
				if (msg2.toLowerCase().contains("&")) {
		            msg2 = msg2.replaceAll("&", "§");
				player.sendMessage(msg2);
				return true;
		        }
           	 }
			} catch (IOException e) {
		            msg3 = msg3.replaceAll("&", "§");
				player.sendMessage(msg3);
				return true;
			}
			}
		}
		
		if(split[0].equalsIgnoreCase("/denyrules")){
			if (isInList(player.getName())){
				msg7 = msg7.replaceAll("&", "§");
				player.sendMessage(msg7);
				return true;
			}else{
			if (msg4.toLowerCase().contains("&")) {
	            msg4 = msg4.replaceAll("&", "§");
			player.kick(msg4);
			return true;
			}
	        }
		}
		
	if(split[0].equalsIgnoreCase("/rules")){
		if (!isInList(player.getName())){
		msg6 = msg6.replaceAll("&", "§");
		player.sendMessage(msg6);
		}
		    try {
		      FileInputStream fis = new FileInputStream("plugins/config/RCrules.txt");
		      Scanner scanner = new Scanner(fis, "UTF-8");
		      scanner.useDelimiter("\r\n");
		      while (scanner.hasNext()) {
		        String line = scanner.nextLine();
		        if (line.startsWith("#")) {
		          continue;
		        }
		        if (line.toLowerCase().contains("&")) {
		            line = line.replaceAll("&", "§");
		          player.sendMessage(line);
		        }
		      }
		      scanner.close();
		      fis.close();
		    }catch (IOException e) {
		            msg5 = msg5.replaceAll("&", "§");
				player.sendMessage(msg5);
				return true;
		    }
		    return true;
		}
	return false;
	}
	
	public boolean isInList(String playername) {
	    try {
	      BufferedReader in = new BufferedReader(new FileReader(props));
	      String line = in.readLine();
	      while (line != null) {
	        if (line.equalsIgnoreCase(playername)) {
	          in.close();
	          return true;
	        }
	        line = in.readLine();
	      }
	      in.close();
	    } catch (IOException localIOException) {
	    }
	    return false; }
	
    public Location findPlaceToStand(Player player, String direction)
    {
      int step;
      if (direction.equals("up")) {
        step = 1;
      }
      else
      {
        if (direction.equals("down"))
          step = -1;
        else
          return null;
      }
      int x = (int)Math.round(player.getX() - 0.5D);
      int y = (int)Math.round(player.getY() + step);
      int z = (int)Math.round(player.getZ() - 0.5D);
      World s = player.getWorld();
      while ((2 < y) && (y < 125)) {
        if ((s.getBlockIdAt(x, y, z) != 0) && (s.getBlockIdAt(x, y + 1, z) == 0) && (s.getBlockIdAt(x, y + 2, z) == 0)) {
          return new Location(x + 0.5D, y + 1.0D, z + 0.5D, player.getRotation(), player.getPitch());
        }
        y += step;
      }
      return null;
    }
}