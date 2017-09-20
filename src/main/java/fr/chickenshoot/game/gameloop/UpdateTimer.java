//package fr.chickenshoot.game.gameloop;
//
//import fr.lordkadoc.launcher.ServerInstance;
//
//public class UpdateTimer extends GameLoopOperation {
//	
//	public UpdateTimer(ServerInstance instance, long delay) {
//		super(instance, delay);
//	}
//
//	@Override
//	protected void update() {
//		
//		if(instance.getState() == ServerInstance.RUNNING){
//			if(instance.getCarte().isGameFinished() || instance.getTime() <= 0)
//				setEnded();
//			else
//				instance.setTime(instance.getTime()-1);
//		}else if(instance.getState() == ServerInstance.ENDED){
//			if(instance.getTime() <= 0)
//				instance.setState(ServerInstance.STOP);
//			else
//				instance.setTime(instance.getTime()-1);
//		}
//		
//	}
//	
//	private void setEnded(){
//		instance.setTime(5);
//		instance.setState(ServerInstance.ENDED);
//	}
//
//}
