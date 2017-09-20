//package fr.chickenshoot.game.entities;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class PlayerActionListener {
//	
//	protected Player player;
//	
//	protected Map<Action,ActionHandler> handlers;
//	
//	public PlayerActionListener(Player player) {
//		this.player = player;
//		handlers = new HashMap<Action,ActionHandler>();
//		initHandler();
//	}
//	
//	public void initHandler() {
//		addHandler(Action.MOVE_NORTH, new MoveHandler(0, player));
//		addHandler(Action.MOVE_WEST, new MoveHandler(1, player));
//		addHandler(Action.MOVE_SOUTH, new MoveHandler(2, player));
//		addHandler(Action.MOVE_EAST, new MoveHandler(3, player));
//		addHandler(Action.SHOOT, new ShootHandler(player));
//	}
//	
//	public void addHandler(Action action, ActionHandler handler){
//		handlers.put(action, handler);
//	}
//	
//	/**
//	 * Gère la résolution d'une action du joueur. Si le listener ne gère pas ce type d'action, il ne se passe rien.
//	 * 
//	 * @param action l'action à gérer
//	 */
//	public void handle(Action action) {
//		ActionHandler handler = handlers.get(action);
//		if(handler != null){
//			handler.handle();
//		}
//	}
//	
//}
