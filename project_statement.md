# Project Statement: Enhanced Recipe Recommender System

## Problem Statement
Every day, individuals struggle to decide what to cook due to a combination of decision fatigue, specific dietary restrictions, limited time, and nutritional goals. Existing recipe platforms often overwhelm users with endless choices without providing personalized, data-driven insights. Furthermore, users often cannot accurately gauge how long a new recipe will take or how its nutritional profile compares to others in the same cuisine. 

## Scope of the Project
This project provides a standalone, data-driven Java application that operates via a console-based interactive menu, augmented by graphical data visualizations. 
The scope includes:
* A curated dataset of 50 recipes across 8 distinct global cuisines.
* A multi-parameter filtering and ranking engine.
* Statistical computation modules (Mean, Standard Deviation, Percentiles, Correlation).
* A custom mathematical implementation of Multiple Linear Regression for predictive analytics.
* Java Swing-based visual plotting (Bar, Box, Pie, Heatmap, 3D projections) implemented entirely from scratch without external charting libraries.

## Target Users
* **Home Cooks:** Looking for quick, daily meal ideas based on what they have time for.
* **Health-Conscious Individuals:** Users needing strict control over calorie intake and seeking "healthy" algorithmic scoring.
* **Culinary Students/Enthusiasts:** Users interested in the statistical breakdown and complexity metrics of different global cuisines.

## High-Level Features
1. **Dynamic Recommendation Engine:** Multi-criteria filtering with a weighted scoring system to surface the most relevant recipes.
2. **Nutritional & Complexity Profiling:** Mathematical analysis of recipes to determine nutritional density and complexity scores.
3. **Predictive Modeling:** Calculates expected cooking times using regression equations trained on the internal dataset.
4. **Comprehensive Data Visualization:** Generates visual dashboards to analyze the dataset's correlations, distributions, and trends.
5. **Actionable Outputs:** Provides one-click YouTube search links for the top recommended meals.