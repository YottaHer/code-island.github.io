package Tests_v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ testAdvancedCritter.class, testAttack.class,
		testBasicCritter.class, testCalculateBox.class,
		testcalculategrid.class, testCalculateGridPositon.class,
		testCheck.class, testcheckmap.class, testCheckTowerPosition.class,
		testCheckTowerPresence.class, testCheckValid.class,
		testCritterFactory.class, testCurrencyInc.class, testDefineEntry.class,
		testDefineExit.class, testFindeTarget.class, testFindTarget2.class,
		testGameController.class, testGameSingleton.class,
		testInitialDirection.class, testInitialWave.class, testInRange.class,
		testIntermediateCritter.class, testLoadGame.class, testLoadMap.class,
		testLoseCoin.class, testMobFreeze.class, testMobHurt.class,
		testMobWalk.class, testNearExit.class, testNearTower.class,
		testPanleDefineEntry.class, testPanleExitPoint.class,
		testRemoveTower.class, testRenewScore.class, testSaveGame.class,
		testSetStrategy.class, testSplash.class, testStrongest.class,
		testTowerAttack2.class, testTowerCharacterWindow.class,
		testUpgrade.class, testUpgrade2.class, testWeakest.class })
public class AllTests {

}
