# Enhanced Recipe Recommender System

## Overview
The Enhanced Recipe Recommender System is a Java-based application designed to help users find the perfect meal based on their preferences, dietary restrictions, and nutritional goals. Going beyond simple filtering, the system incorporates statistical analysis, nutritional profiling, and multiple linear regression to predict cooking times. It also features a robust suite of data visualizations built from scratch using Java Swing.

## Features
* **Personalized Recommendations:** Filter recipes by cuisine, maximum cooking time, dietary preference (Vegetarian/Non-Vegetarian), calorie limits, and minimum ratings.
* **Custom Recommendation Algorithm:** Ranks recipes using a weighted score based on user ratings, cooking time, recipe complexity, and calorie count.
* **Nutritional Analysis:** Calculates health scores, average calories, and ingredient density, highlighting the top healthiest recipes.
* **Cooking Time Prediction (Machine Learning):** Uses a custom-built multiple linear regression model to predict how long a recipe will take based on its difficulty and ingredient count.
* **Data Visualization (GUI):** Generates interactive Java Swing windows containing:
  * Bar Charts (Average cooking time by cuisine)
  * Box Plots (Rating distributions)
  * Pie Charts (Difficulty level distribution)
  * Correlation Heatmaps (Relationships between time, rating, calories, etc.)
  * 3D Regression Plots (Visualizing the predictive model)
* **YouTube Integration:** Automatically generates optimized YouTube search URLs for recommended recipes.

## Technologies Used
* **Language:** Java (JDK 8 or higher recommended)
* **GUI Framework:** Java Swing & AWT (Built-in)
* **Data Structures:** Java Collections Framework (Lists, Maps, Arrays)

## Steps to Install & Run
1. Ensure you have the Java Development Kit (JDK) installed on your system.
2. Clone or download this repository.
3. Open a terminal or command prompt and navigate to the directory containing `RecipeRecommender.java`.
4. Compile the source code:
   ```bash
   javac RecipeRecommender.java
   ```
5. Run the application:
   ```bash
   java RecipeRecommender
   ```

## Instructions for Testing
1. **Interactive Prompts:** When running the application, test the console inputs by providing various combinations of cuisines (e.g., Italian, Indian), time limits, and calorie limits. 
2. **Error Handling:** Try pressing `Enter` without typing anything to test the bypass/default features, or enter letters when numbers are expected to verify the error catching.
3. **Visualization Checks:** After completing the text prompts, wait for the Java Swing windows to appear. Ensure that the 2x2 dashboard and the 3D regression window render correctly. 

## Screenshots
*(Note to student: Add your actual screenshots here before uploading to GitHub)*
* `screenshot_console_output.png` - Showing the recommendation list.
* `screenshot_dashboard.png` - Showing the 4 statistical charts.
* `screenshot_3d_plot.png` - Showing the 3D linear regression visualization.