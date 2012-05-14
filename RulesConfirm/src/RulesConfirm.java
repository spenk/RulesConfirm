import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class RulesConfirm extends Plugin
{
	  String name = "RulesConfirm";
	  String version = "1.0";
	  String author = " spenk";
	  static Logger log = Logger.getLogger("Minecraft");
	  
	  
public void initialize(){
	RulesConfirmListener listener = new RulesConfirmListener();
log.info(this.name +" version "+ this.version + " by " +this.author+(" is initialized!"));
etc.getLoader().addListener(PluginLoader.Hook.COMMAND, listener, this, PluginListener.Priority.MEDIUM);
etc.getLoader().addListener(PluginLoader.Hook.CHAT, listener, this, PluginListener.Priority.MEDIUM);
etc.getLoader().addListener(PluginLoader.Hook.PLAYER_MOVE, listener, this, PluginListener.Priority.MEDIUM);
etc.getLoader().addListener(PluginLoader.Hook.BLOCK_BROKEN, listener, this, PluginListener.Priority.MEDIUM);
etc.getLoader().addListener(PluginLoader.Hook.BLOCK_CREATED, listener, this, PluginListener.Priority.MEDIUM);
etc.getLoader().addListener(PluginLoader.Hook.LOGIN, listener, this, PluginListener.Priority.MEDIUM);
etc.getLoader().addListener(PluginLoader.Hook.ITEM_DROP, listener, this, PluginListener.Priority.MEDIUM);
etc.getLoader().addListener(PluginLoader.Hook.ITEM_PICK_UP, listener, this, PluginListener.Priority.MEDIUM);
etc.getLoader().addListener(PluginLoader.Hook.OPEN_INVENTORY, listener, this, PluginListener.Priority.MEDIUM);
etc.getLoader().addListener(PluginLoader.Hook.ITEM_USE, listener, this, PluginListener.Priority.MEDIUM);
File f = new File("plugins/config");
f.mkdir();
listener.loadConfiguration();
try {
  	 if (!new File(listener.rule).exists()) {
  		 listener.rules.createNewFile();
  	 }
} catch (IOException e) {
	log.info("o.O an error occured due creating rules file, please reload the plugin.");
}
try {
 	 if (!new File(listener.file).exists()) {
 		 listener.props.createNewFile();
 	 }
} catch (IOException e) {
	log.info("o.O an error occured due creating rules file, please reload the plugin.");
}
}

public void enable(){
	etc.getInstance().addCommand("/rules", "Shows you the rules");
	log.info(this.name + " version " + this.version + " by " + this.author + " is enabled!");
}

public void disable(){
	
	log.info(this.name + " version " + this.version + " is disabled!");
}
}