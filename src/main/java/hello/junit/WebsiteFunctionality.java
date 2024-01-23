/*****************************************************************
 * Vip Service http://www.vipservice.ru
 * Project: webtests
 *
 * $Id: $
 *****************************************************************/
package hello.junit;

public enum WebsiteFunctionality {

    NEW_TESTS,
    NEW_TESTS2,

    /**
     * Used for tests running after main assembly and clean data created by
     * tests
     */
    OPERATION_CLEANING,

    /**
     * Used for tests running before main assembly to clean data that can affect
     * main tests (notifications, etc.)
     */
    OPERATION_PRE_CLEANING,

    /**
     * Used for fast check of base functionality
     */
    BASE_FUNCTIONALITY_CHECK,

    /**
     * Изменение тестовых параметров
     */
    SYSTEM_UPDATE
}
