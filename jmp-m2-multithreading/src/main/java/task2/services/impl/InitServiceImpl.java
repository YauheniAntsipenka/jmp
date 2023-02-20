package task2.services.impl;

import task2.services.api.InitService;

/**
 * Initializer
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class InitServiceImpl implements InitService {

    @Override
    public int[] initializeArray() {
        return new int[]{5, 9, 4, 6, 5, 3, -1, 3, 5, -45, 45, 2};
    }
}
