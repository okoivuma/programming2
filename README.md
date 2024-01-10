# Programming 2 Spring 2024

## READ the following instructions carefully.

- This repository will contain the source code for the homework assignments for the Programming2 course.
- There is a dedicated folder for each homework. Source code files for each homework should be placed in the corresponding homework folder.
  - For example, the source code for homework1 should be in the following subfolder: `homework1/src/main/java/dev/m3s/programming2/homework1/`
- The instructions for implementing and submitting homeworks are available on Moodle.
- The directory structure should not be changed.
- Existing files and folders in this template should not be removed.
- Creating a new tag is required to rerun the pipeline every time the source code is updated.
- Tags names should comply with the required format, i.e., homeworkX_vY_CustomPart, where X is the homework number (1, 2, 3, or 4), and Y is the version number (1 or 2).
  - The custom part can be chosen freely. However, incremental numbers 1,2,3, etc., are recommended.
    Examples: homework1_v1_1, homework1_v1_2, homework1_v2_1, homework1_v2_2
- When you finish the homework, create a final tag in the following format homeworkX_vY_submission, where X is the homework number, and Y is the version number you have implemented

## Adding the pipeline tests to your repository

The repository containing the tests can be found in the [CI repository](https://ips-ci.oulu.fi/programming2/spring2024/spring2024_ci).
The instructions for adding the pipeline tests to your repository are available in the readme file of the CI repository.  
You don't have to add or commit the tests to this repository, since the pipeline has been configured to use the CI repository automatically.
The tests are provided for your convenience, and you are not required to use them locally.
To run tag dependent tests locally, you can remove any row beginning with `@EnabledIf` or `@DisabledIf` from the test classes.

The tests are provided as a separate repository to make it easier to update the tests in the future.
Therefore it is important to periodically check that your local tests are up to date with the CI repository.
