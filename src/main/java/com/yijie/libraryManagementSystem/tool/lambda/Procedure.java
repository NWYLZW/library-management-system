package com.yijie.libraryManagementSystem.tool.lambda;

/**
 * @desc    Procedure.java
 * @author  yijie
 * @date    2021-01-06 19:37
 * @note    2021-01-06 19:37 yijie Created Procedure.java file
 */
@FunctionalInterface
public interface Procedure {
    void run();

    default Procedure then(Procedure after){
        return () -> {
            this.run();
            after.run();
        };
    }

    default Procedure prior(Procedure before){
        return () -> {
            before.run();
            this.run();
        };
    }
}
