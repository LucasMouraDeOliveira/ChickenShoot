package fr.refactoring.game.system;

import com.badlogic.ashley.core.ComponentMapper;

import fr.refactoring.game.component.AngleComponent;
import fr.refactoring.game.component.HealthComponent;
import fr.refactoring.game.component.KeyboardComponent;
import fr.refactoring.game.component.PositionComponent;
import fr.refactoring.game.component.SizeComponent;
import fr.refactoring.game.component.SpeedComponent;
import fr.refactoring.game.component.VelocityComponent;
import fr.refactoring.game.component.VisibilityComponent;
import fr.refactoring.game.component.WeaponComponent;

public class Mapper {
	
	public static final ComponentMapper<AngleComponent> angleMapper = ComponentMapper.getFor(AngleComponent.class);
	
	public static final ComponentMapper<HealthComponent> healthMapper = ComponentMapper.getFor(HealthComponent.class);

	public static final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
	
	public static final ComponentMapper<KeyboardComponent> keyboardMapper = ComponentMapper.getFor(KeyboardComponent.class);
	
	public static final ComponentMapper<SpeedComponent> speedMapper = ComponentMapper.getFor(SpeedComponent.class);
	
	public static final ComponentMapper<VisibilityComponent> visibilityMapper = ComponentMapper.getFor(VisibilityComponent.class);

	public static final ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
	
	public static final ComponentMapper<WeaponComponent> weaponMapper = ComponentMapper.getFor(WeaponComponent.class);

	public static final ComponentMapper<SizeComponent> sizeMapper = ComponentMapper.getFor(SizeComponent.class) ;
	
}
