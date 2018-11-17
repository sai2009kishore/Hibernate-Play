package services;

import com.google.inject.Inject;

import models.Accessory;
import repositories.AccessoryRepository;

public class AccessoryService extends AbstractService<Accessory> {

	private final AccessoryRepository accessoryRepository;

	@Inject
	public AccessoryService(AccessoryRepository accessoryRepository) {
		super(accessoryRepository);
		this.accessoryRepository = accessoryRepository;
	}
}
