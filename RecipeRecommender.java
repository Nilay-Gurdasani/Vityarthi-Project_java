import java.awt.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.*;
import javax.swing.*;

public class RecipeRecommender {

    static class Recipe {
        String name;
        List<String> ingredients;
        String cuisine;
        int time;
        String difficulty;
        String dietary;
        int calories;
        double rating;
        int difficultyEncoded;
        double complexityScore;
        int ingredientCount;
        double nutritionalDensity;
        double recommendationScore;
        double healthScore;

        Recipe(String name, List<String> ingredients, String cuisine, int time,
               String difficulty, String dietary, int calories, double rating) {
            this.name = name;
            this.ingredients = ingredients;
            this.cuisine = cuisine;
            this.time = time;
            this.difficulty = difficulty;
            this.dietary = dietary;
            this.calories = calories;
            this.rating = rating;
        }
    }

    private final List<Recipe> recipes = new ArrayList<>();

    public RecipeRecommender() {
        loadRecipes();
        preprocessData();
    }

    private void add(String name, String[] ingredients, String cuisine, int time,
                      String difficulty, String dietary, int calories, double rating) {
        recipes.add(new Recipe(name, Arrays.asList(ingredients), cuisine, time, difficulty, dietary, calories, rating));
    }

    private void loadRecipes() {
        add("Caesar Salad", new String[]{"romaine lettuce", "croutons", "parmesan cheese", "caesar dressing"}, "American", 10, "Easy", "Vegetarian", 180, 4.2);
        add("Grilled Cheese Sandwich", new String[]{"bread", "cheese", "butter"}, "American", 15, "Easy", "Vegetarian", 350, 4.0);
        add("Veggie Burger", new String[]{"vegetarian patty", "bun", "lettuce", "tomato"}, "American", 20, "Medium", "Vegetarian", 320, 4.1);
        add("Mac and Cheese", new String[]{"pasta", "cheese", "milk", "butter"}, "American", 30, "Medium", "Vegetarian", 450, 4.4);
        add("Veggie Pizza", new String[]{"pizza dough", "tomato sauce", "cheese", "vegetables"}, "American", 40, "Medium", "Vegetarian", 280, 4.3);

        add("Chicken Caesar Salad", new String[]{"romaine lettuce", "chicken", "croutons", "parmesan cheese", "caesar dressing"}, "American", 10, "Easy", "Non-Vegetarian", 320, 4.5);
        add("Turkey Sandwich", new String[]{"bread", "turkey", "lettuce", "tomato"}, "American", 15, "Easy", "Non-Vegetarian", 380, 4.2);
        add("Chicken Burger", new String[]{"chicken patty", "bun", "lettuce", "tomato"}, "American", 20, "Medium", "Non-Vegetarian", 420, 4.3);
        add("BBQ Chicken", new String[]{"chicken", "bbq sauce", "spices"}, "American", 30, "Medium", "Non-Vegetarian", 350, 4.6);
        add("Steak", new String[]{"beef steak", "salt", "pepper", "butter"}, "American", 40, "Hard", "Non-Vegetarian", 550, 4.7);

        add("Bruschetta", new String[]{"bread", "tomatoes", "basil", "garlic", "olive oil"}, "Italian", 10, "Easy", "Vegetarian", 150, 4.3);
        add("Caprese Salad", new String[]{"tomatoes", "mozzarella", "basil", "olive oil"}, "Italian", 15, "Easy", "Vegetarian", 220, 4.4);
        add("Pasta Primavera", new String[]{"pasta", "vegetables", "parmesan cheese", "olive oil"}, "Italian", 20, "Medium", "Vegetarian", 380, 4.2);
        add("Vegetarian Lasagna", new String[]{"lasagna noodles", "tomato sauce", "ricotta cheese", "spinach", "mozzarella"}, "Italian", 30, "Medium", "Vegetarian", 420, 4.5);
        add("Margherita Pizza", new String[]{"pizza dough", "tomato sauce", "mozzarella", "basil"}, "Italian", 40, "Medium", "Vegetarian", 300, 4.6);

        add("Prosciutto e Melone", new String[]{"prosciutto", "melon"}, "Italian", 10, "Easy", "Non-Vegetarian", 200, 4.1);
        add("Chicken Piccata", new String[]{"chicken", "lemon", "capers", "butter"}, "Italian", 15, "Easy", "Non-Vegetarian", 280, 4.3);
        add("Spaghetti Carbonara", new String[]{"spaghetti", "eggs", "parmesan cheese", "pancetta", "pepper"}, "Italian", 20, "Medium", "Non-Vegetarian", 450, 4.7);
        add("Chicken Alfredo", new String[]{"fettuccine", "chicken", "cream", "parmesan cheese", "garlic"}, "Italian", 30, "Medium", "Non-Vegetarian", 520, 4.4);
        add("Lasagna Bolognese", new String[]{"lasagna noodles", "ground beef", "tomato sauce", "bechamel sauce", "parmesan"}, "Italian", 40, "Hard", "Non-Vegetarian", 480, 4.8);

        add("Masala Chai", new String[]{"tea leaves", "milk", "spices"}, "Indian", 10, "Easy", "Vegetarian", 80, 4.5);
        add("Aloo Paratha", new String[]{"potatoes", "wheat flour", "spices", "butter"}, "Indian", 15, "Medium", "Vegetarian", 320, 4.6);
        add("Palak Paneer", new String[]{"spinach", "paneer", "tomatoes", "spices"}, "Indian", 20, "Medium", "Vegetarian", 280, 4.7);
        add("Vegetable Pulao", new String[]{"rice", "mixed vegetables", "spices"}, "Indian", 30, "Medium", "Vegetarian", 350, 4.4);
        add("Paneer Butter Masala", new String[]{"paneer", "tomato sauce", "cream", "butter", "spices"}, "Indian", 40, "Medium", "Vegetarian", 420, 4.8);

        add("Chicken Pakora", new String[]{"chicken", "chickpea flour", "spices"}, "Indian", 10, "Easy", "Non-Vegetarian", 220, 4.3);
        add("Chicken Curry", new String[]{"chicken", "onions", "tomatoes", "spices"}, "Indian", 15, "Medium", "Non-Vegetarian", 320, 4.5);
        add("Butter Chicken", new String[]{"chicken", "butter", "tomatoes", "cream", "spices"}, "Indian", 20, "Medium", "Non-Vegetarian", 450, 4.8);
        add("Biryani", new String[]{"rice", "chicken", "yogurt", "spices"}, "Indian", 30, "Hard", "Non-Vegetarian", 520, 4.9);
        add("Lamb Rogan Josh", new String[]{"lamb", "yogurt", "spices", "tomatoes"}, "Indian", 40, "Hard", "Non-Vegetarian", 480, 4.7);

        add("Thai Spring Rolls", new String[]{"rice paper", "vegetables", "peanut sauce"}, "Thai", 10, "Easy", "Vegetarian", 120, 4.2);
        add("Papaya Salad", new String[]{"green papaya", "tomatoes", "peanuts", "lime"}, "Thai", 15, "Easy", "Vegetarian", 180, 4.4);
        add("Pad Thai", new String[]{"rice noodles", "tofu", "peanuts", "bean sprouts"}, "Thai", 20, "Medium", "Vegetarian", 380, 4.5);
        add("Green Curry", new String[]{"green curry paste", "coconut milk", "vegetables"}, "Thai", 30, "Medium", "Vegetarian", 320, 4.6);
        add("Vegetarian Massaman Curry", new String[]{"potatoes", "vegetables", "coconut milk", "massaman curry paste"}, "Thai", 40, "Hard", "Vegetarian", 350, 4.3);

        add("Chicken Satay", new String[]{"chicken", "peanut sauce", "spices"}, "Thai", 10, "Easy", "Non-Vegetarian", 280, 4.5);
        add("Tom Yum Soup", new String[]{"shrimp", "mushrooms", "lemongrass", "chili", "lime"}, "Thai", 15, "Medium", "Non-Vegetarian", 180, 4.6);
        add("Chicken Pad Thai", new String[]{"rice noodles", "chicken", "peanuts", "bean sprouts"}, "Thai", 20, "Medium", "Non-Vegetarian", 420, 4.7);
        add("Chicken Green Curry", new String[]{"green curry paste", "coconut milk", "chicken", "vegetables"}, "Thai", 30, "Medium", "Non-Vegetarian", 380, 4.8);
        add("Beef Massaman Curry", new String[]{"beef", "potatoes", "onions", "coconut milk", "massaman curry paste"}, "Thai", 40, "Hard", "Non-Vegetarian", 450, 4.4);

        add("Hummus", new String[]{"chickpeas", "tahini", "lemon juice", "garlic", "olive oil"}, "Middle Eastern", 10, "Easy", "Vegetarian", 160, 4.6);
        add("Tabbouleh", new String[]{"bulgur", "tomatoes", "cucumbers", "parsley", "lemon juice"}, "Middle Eastern", 15, "Easy", "Vegetarian", 140, 4.3);
        add("Falafel", new String[]{"chickpeas", "garlic", "onions", "spices", "herbs"}, "Middle Eastern", 20, "Medium", "Vegetarian", 320, 4.7);
        add("Vegetarian Shawarma", new String[]{"pita bread", "vegetables", "tahini", "spices"}, "Middle Eastern", 30, "Medium", "Vegetarian", 280, 4.4);
        add("Vegetarian Kebabs", new String[]{"vegetables", "spices", "yogurt"}, "Middle Eastern", 40, "Hard", "Vegetarian", 240, 4.2);

        add("Chicken Shawarma", new String[]{"chicken", "spices", "yogurt", "pita bread"}, "Middle Eastern", 10, "Easy", "Non-Vegetarian", 320, 4.8);
        add("Kofta", new String[]{"ground lamb", "spices", "herbs"}, "Middle Eastern", 15, "Medium", "Non-Vegetarian", 280, 4.5);
        add("Lamb Kebabs", new String[]{"lamb", "spices", "yogurt"}, "Middle Eastern", 20, "Medium", "Non-Vegetarian", 350, 4.6);
        add("Beef Shawarma", new String[]{"beef", "spices", "yogurt", "pita bread"}, "Middle Eastern", 30, "Medium", "Non-Vegetarian", 380, 4.7);
        add("Lamb Mansaf", new String[]{"lamb", "yogurt", "spices", "rice"}, "Middle Eastern", 40, "Hard", "Non-Vegetarian", 520, 4.9);

        add("Kimchi", new String[]{"cabbage", "salt", "chili powder", "garlic", "ginger"}, "Korean", 10, "Easy", "Vegetarian", 40, 4.5);
        add("Kimbap", new String[]{"rice", "seaweed", "vegetables", "egg", "pickled radish"}, "Korean", 15, "Medium", "Vegetarian", 280, 4.6);
        add("Bibimbap", new String[]{"rice", "vegetables", "gochujang", "egg", "sesame oil"}, "Korean", 20, "Medium", "Vegetarian", 420, 4.8);
        add("Japchae", new String[]{"sweet potato noodles", "vegetables", "soy sauce", "sugar", "sesame oil"}, "Korean", 30, "Medium", "Vegetarian", 320, 4.7);
        add("Vegetarian Tteokbokki", new String[]{"rice cakes", "gochujang", "vegetables", "green onions"}, "Korean", 40, "Medium", "Vegetarian", 350, 4.4);

        add("Bulgogi", new String[]{"beef", "soy sauce", "sugar", "garlic", "sesame oil"}, "Korean", 15, "Easy", "Non-Vegetarian", 380, 4.9);
        add("Kimchi Jjigae", new String[]{"kimchi", "pork", "tofu", "onion", "garlic", "gochujang"}, "Korean", 20, "Medium", "Non-Vegetarian", 280, 4.7);
        add("Samgyeopsal", new String[]{"pork belly", "lettuce", "garlic", "ssamjang", "green onions"}, "Korean", 30, "Easy", "Non-Vegetarian", 450, 4.8);
        add("Dak Galbi", new String[]{"chicken", "gochujang", "vegetables", "garlic", "onions"}, "Korean", 30, "Medium", "Non-Vegetarian", 380, 4.6);
        add("Galbi", new String[]{"short ribs", "soy sauce", "sugar", "garlic", "sesame oil"}, "Korean", 40, "Hard", "Non-Vegetarian", 520, 4.9);

        add("Guacamole", new String[]{"avocado", "tomato", "onion", "lime", "cilantro"}, "Mexican", 10, "Easy", "Vegetarian", 160, 4.7);
        add("Vegetarian Quesadilla", new String[]{"tortilla", "cheese", "beans", "vegetables"}, "Mexican", 15, "Easy", "Vegetarian", 320, 4.4);
        add("Bean Burrito", new String[]{"tortilla", "beans", "rice", "cheese", "salsa"}, "Mexican", 20, "Medium", "Vegetarian", 380, 4.5);
        add("Vegetarian Enchiladas", new String[]{"tortillas", "beans", "cheese", "sauce", "vegetables"}, "Mexican", 30, "Medium", "Vegetarian", 420, 4.6);
        add("Chiles Rellenos", new String[]{"poblano peppers", "cheese", "batter", "sauce"}, "Mexican", 40, "Hard", "Vegetarian", 350, 4.3);

        add("Chicken Tacos", new String[]{"tortilla", "chicken", "lettuce", "tomato", "salsa"}, "Mexican", 10, "Easy", "Non-Vegetarian", 280, 4.6);
        add("Beef Quesadilla", new String[]{"tortilla", "beef", "cheese", "vegetables"}, "Mexican", 15, "Easy", "Non-Vegetarian", 380, 4.5);
        add("Carnitas", new String[]{"pork", "orange", "spices", "onions"}, "Mexican", 20, "Medium", "Non-Vegetarian", 420, 4.7);
        add("Chicken Enchiladas", new String[]{"tortillas", "chicken", "cheese", "sauce", "vegetables"}, "Mexican", 30, "Medium", "Non-Vegetarian", 450, 4.8);
        add("Beef Barbacoa", new String[]{"beef", "chili", "spices", "herbs"}, "Mexican", 40, "Hard", "Non-Vegetarian", 380, 4.4);

        add("Edamame", new String[]{"soybeans", "salt"}, "Japanese", 10, "Easy", "Vegetarian", 120, 4.3);
        add("Vegetable Tempura", new String[]{"vegetables", "batter", "oil"}, "Japanese", 15, "Medium", "Vegetarian", 280, 4.5);
        add("Vegetable Sushi", new String[]{"rice", "seaweed", "cucumber", "avocado", "carrot"}, "Japanese", 20, "Medium", "Vegetarian", 220, 4.6);
        add("Miso Soup", new String[]{"miso paste", "tofu", "seaweed", "green onions"}, "Japanese", 30, "Easy", "Vegetarian", 80, 4.4);
        add("Vegetable Ramen", new String[]{"noodles", "vegetable broth", "vegetables", "tofu"}, "Japanese", 40, "Medium", "Vegetarian", 380, 4.7);

        add("Chicken Teriyaki", new String[]{"chicken", "teriyaki sauce", "rice", "vegetables"}, "Japanese", 10, "Easy", "Non-Vegetarian", 320, 4.7);
        add("Salmon Sushi", new String[]{"rice", "seaweed", "salmon", "avocado"}, "Japanese", 15, "Medium", "Non-Vegetarian", 280, 4.8);
        add("Beef Sukiyaki", new String[]{"beef", "tofu", "vegetables", "noodles", "sauce"}, "Japanese", 20, "Medium", "Non-Vegetarian", 420, 4.6);
        add("Tonkatsu", new String[]{"pork", "breading", "cabbage", "sauce"}, "Japanese", 30, "Medium", "Non-Vegetarian", 480, 4.5);
        add("Chicken Ramen", new String[]{"noodles", "chicken broth", "chicken", "vegetables", "egg"}, "Japanese", 40, "Hard", "Non-Vegetarian", 450, 4.9);
    }

    private int encodeDifficulty(String difficulty) {
        if (difficulty.equals("Easy")) return 0;
        if (difficulty.equals("Hard")) return 1;
        return 2;
    }

    private void preprocessData() {
        for (Recipe r : recipes) {
            r.difficultyEncoded = encodeDifficulty(r.difficulty);
            r.ingredientCount = r.ingredients.size();
            r.complexityScore = r.time * 0.4 + r.difficultyEncoded * 30 + r.rating * 10 + r.calories * 0.01;
            r.nutritionalDensity = (double) r.calories / r.ingredientCount;
        }
    }

    public List<Recipe> recommendRecipes(String cuisine, Integer maxTime, String dietary, double minRating, Integer maxCalories) {
        List<Recipe> filtered = new ArrayList<>();
        for (Recipe r : recipes) {
            if (cuisine != null && !r.cuisine.equalsIgnoreCase(cuisine)) continue;
            if (maxTime != null && r.time > maxTime) continue;
            if (dietary != null && !r.dietary.equalsIgnoreCase(dietary)) continue;
            if (maxCalories != null && r.calories > maxCalories) continue;
            if (r.rating < minRating) continue;
            r.recommendationScore = r.rating * 0.4 + (1.0 / r.time) * 25 + (1.0 / r.complexityScore) * 20 + (1.0 / r.calories) * 15;
            filtered.add(r);
        }
        filtered.sort((a, b) -> Double.compare(b.recommendationScore, a.recommendationScore));
        return filtered;
    }

    static String repeat(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(s);
        return sb.toString();
    }

    static double mean(double[] v) {
        double s = 0;
        for (double x : v) s += x;
        return s / v.length;
    }

    static double std(double[] v) {
        if (v.length < 2) return 0;
        double m = mean(v);
        double s = 0;
        for (double x : v) s += (x - m) * (x - m);
        return Math.sqrt(s / (v.length - 1));
    }

    static double min(double[] v) {
        double m = v[0];
        for (double x : v) if (x < m) m = x;
        return m;
    }

    static double max(double[] v) {
        double m = v[0];
        for (double x : v) if (x > m) m = x;
        return m;
    }

    static double percentile(double[] sorted, double p) {
        double idx = p / 100.0 * (sorted.length - 1);
        int lo = (int) Math.floor(idx);
        int hi = (int) Math.ceil(idx);
        if (lo == hi) return sorted[lo];
        double frac = idx - lo;
        return sorted[lo] + (sorted[hi] - sorted[lo]) * frac;
    }

    static double correlation(double[] x, double[] y) {
        double mx = mean(x), my = mean(y);
        double sxy = 0, sx = 0, sy = 0;
        for (int i = 0; i < x.length; i++) {
            sxy += (x[i] - mx) * (y[i] - my);
            sx += (x[i] - mx) * (x[i] - mx);
            sy += (y[i] - my) * (y[i] - my);
        }
        return sxy / Math.sqrt(sx * sy);
    }

    public void analyzeCuisineStats() {
        Map<String, List<Recipe>> byCuisine = new LinkedHashMap<>();
        for (Recipe r : recipes) byCuisine.computeIfAbsent(r.cuisine, k -> new ArrayList<>()).add(r);

        System.out.printf("%-16s %8s %8s %8s %8s %8s %8s %9s %9s %8s %8s%n",
                "Cuisine", "TimeAvg", "TimeStd", "TimeMin", "TimeMax", "RateAvg", "RateStd", "CalAvg", "CalStd", "IngAvg", "CompAvg");

        for (Map.Entry<String, List<Recipe>> e : byCuisine.entrySet()) {
            List<Recipe> list = e.getValue();
            double[] times = list.stream().mapToDouble(r -> r.time).toArray();
            double[] ratings = list.stream().mapToDouble(r -> r.rating).toArray();
            double[] calories = list.stream().mapToDouble(r -> r.calories).toArray();
            double[] ingredientCounts = list.stream().mapToDouble(r -> r.ingredientCount).toArray();
            double[] complexity = list.stream().mapToDouble(r -> r.complexityScore).toArray();

            System.out.printf("%-16s %8.2f %8.2f %8.2f %8.2f %8.2f %8.2f %9.2f %9.2f %8.2f %8.2f%n",
                    e.getKey(), mean(times), std(times), min(times), max(times),
                    mean(ratings), std(ratings), mean(calories), std(calories),
                    mean(ingredientCounts), mean(complexity));
        }
    }

    static double[] solveLinearSystem(double[][] a, double[] b) {
        int n = b.length;
        double[][] m = new double[n][n];
        double[] rhs = new double[n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(a[i], 0, m[i], 0, n);
            rhs[i] = b[i];
        }
        for (int col = 0; col < n; col++) {
            int pivot = col;
            for (int row = col + 1; row < n; row++) {
                if (Math.abs(m[row][col]) > Math.abs(m[pivot][col])) pivot = row;
            }
            double[] tmpRow = m[col]; m[col] = m[pivot]; m[pivot] = tmpRow;
            double tmpVal = rhs[col]; rhs[col] = rhs[pivot]; rhs[pivot] = tmpVal;

            for (int row = col + 1; row < n; row++) {
                double factor = m[row][col] / m[col][col];
                for (int k = col; k < n; k++) m[row][k] -= factor * m[col][k];
                rhs[row] -= factor * rhs[col];
            }
        }
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = rhs[i];
            for (int j = i + 1; j < n; j++) sum -= m[i][j] * x[j];
            x[i] = sum / m[i][i];
        }
        return x;
    }

    static double[] linearRegression(double[][] x, double[] y) {
        int n = x.length;
        int p = x[0].length;
        int cols = p + 1;
        double[][] xi = new double[n][cols];
        for (int i = 0; i < n; i++) {
            xi[i][0] = 1.0;
            for (int j = 0; j < p; j++) xi[i][j + 1] = x[i][j];
        }
        double[][] xtx = new double[cols][cols];
        double[] xty = new double[cols];
        for (int a = 0; a < cols; a++) {
            for (int b = 0; b < cols; b++) {
                double s = 0;
                for (int i = 0; i < n; i++) s += xi[i][a] * xi[i][b];
                xtx[a][b] = s;
            }
            double s = 0;
            for (int i = 0; i < n; i++) s += xi[i][a] * y[i];
            xty[a] = s;
        }
        return solveLinearSystem(xtx, xty);
    }

    public int predictCookingTime(int difficultyEncoded, int ingredientCount) {
        double[][] x = new double[recipes.size()][2];
        double[] y = new double[recipes.size()];
        for (int i = 0; i < recipes.size(); i++) {
            Recipe r = recipes.get(i);
            x[i][0] = r.difficultyEncoded;
            x[i][1] = r.ingredientCount;
            y[i] = r.time;
        }
        double[] beta = linearRegression(x, y);
        double prediction = beta[0] + beta[1] * difficultyEncoded + beta[2] * ingredientCount;
        return (int) Math.ceil(prediction);
    }

    static class NutritionalAnalysis {
        double avgCalories;
        double stdCalories;
        double avgIngredients;
        List<Recipe> healthiestRecipes;
    }

    public NutritionalAnalysis getNutritionalAnalysis() {
        NutritionalAnalysis analysis = new NutritionalAnalysis();
        double[] calories = recipes.stream().mapToDouble(r -> r.calories).toArray();
        double[] ingredientCounts = recipes.stream().mapToDouble(r -> r.ingredientCount).toArray();
        analysis.avgCalories = mean(calories);
        analysis.stdCalories = std(calories);
        analysis.avgIngredients = mean(ingredientCounts);

        for (Recipe r : recipes) r.healthScore = (1.0 / r.calories) * r.rating * 100;

        List<Recipe> sorted = new ArrayList<>(recipes);
        sorted.sort((a, b) -> Double.compare(b.healthScore, a.healthScore));
        analysis.healthiestRecipes = sorted.subList(0, Math.min(5, sorted.size()));
        return analysis;
    }

    public String getYoutubeSearchLink(String recipeName) {
        String encoded;
        try {
            encoded = URLEncoder.encode(recipeName + " recipe", "UTF-8").replace("+", "%20");
        } catch (java.io.UnsupportedEncodingException e) {
            encoded = recipeName.replace(" ", "%20");
        }
        return "https://www.youtube.com/results?search_query=" + encoded;
    }

    static Color ratingToColor(double rating, double minR, double maxR) {
        double t = (maxR - minR) < 1e-9 ? 0.5 : (rating - minR) / (maxR - minR);
        int r = (int) (t * 255);
        int b = (int) ((1 - t) * 255);
        return new Color(r, 60, b);
    }

    class BarChartPanel extends JPanel {
        String[] labels;
        double[] values;
        String title;
        String yLabel;

        BarChartPanel(String[] labels, double[] values, String title, String yLabel) {
            this.labels = labels;
            this.values = values;
            this.title = title;
            this.yLabel = yLabel;
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();
            int marginLeft = 60, marginBottom = 80, marginTop = 40, marginRight = 20;
            int plotW = w - marginLeft - marginRight;
            int plotH = h - marginTop - marginBottom;
            double maxVal = max(values) * 1.15;

            g2.setColor(Color.BLACK);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14f));
            g2.drawString(title, w / 2 - title.length() * 3, 20);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 11f));
            g2.drawLine(marginLeft, marginTop, marginLeft, marginTop + plotH);
            g2.drawLine(marginLeft, marginTop + plotH, marginLeft + plotW, marginTop + plotH);
            g2.drawString(yLabel, 10, marginTop);

            int barWidth = plotW / values.length;
            Color[] palette = {new Color(141, 211, 199), new Color(255, 255, 179), new Color(190, 186, 218),
                    new Color(251, 128, 114), new Color(128, 177, 211), new Color(253, 180, 98),
                    new Color(179, 222, 105), new Color(252, 205, 229)};
            for (int i = 0; i < values.length; i++) {
                int barH = (int) (values[i] / maxVal * plotH);
                int x = marginLeft + i * barWidth + 5;
                int y = marginTop + plotH - barH;
                g2.setColor(palette[i % palette.length]);
                g2.fillRect(x, y, barWidth - 10, barH);
                g2.setColor(Color.BLACK);
                g2.drawRect(x, y, barWidth - 10, barH);

                Graphics2D gr = (Graphics2D) g2.create();
                gr.translate(x + (barWidth - 10) / 2.0, marginTop + plotH + 12);
                gr.rotate(-Math.PI / 4);
                gr.drawString(labels[i], 0, 0);
                gr.dispose();
            }
        }
    }

    class BoxPlotPanel extends JPanel {
        String[] labels;
        double[][] groups;
        String title;
        String yLabel;

        BoxPlotPanel(String[] labels, double[][] groups, String title, String yLabel) {
            this.labels = labels;
            this.groups = groups;
            this.title = title;
            this.yLabel = yLabel;
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();
            int marginLeft = 60, marginBottom = 80, marginTop = 40, marginRight = 20;
            int plotW = w - marginLeft - marginRight;
            int plotH = h - marginTop - marginBottom;

            double globalMin = Double.MAX_VALUE, globalMax = -Double.MAX_VALUE;
            for (double[] grp : groups) {
                globalMin = Math.min(globalMin, min(grp));
                globalMax = Math.max(globalMax, max(grp));
            }
            double range = globalMax - globalMin;
            globalMin -= range * 0.1;
            globalMax += range * 0.1;

            g2.setColor(Color.BLACK);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14f));
            g2.drawString(title, w / 2 - title.length() * 3, 20);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 11f));
            g2.drawLine(marginLeft, marginTop, marginLeft, marginTop + plotH);
            g2.drawLine(marginLeft, marginTop + plotH, marginLeft + plotW, marginTop + plotH);
            g2.drawString(yLabel, 10, marginTop);

            int slotWidth = plotW / groups.length;
            for (int i = 0; i < groups.length; i++) {
                double[] sorted = groups[i].clone();
                Arrays.sort(sorted);
                double q1 = percentile(sorted, 25);
                double med = percentile(sorted, 50);
                double q3 = percentile(sorted, 75);
                double lo = min(sorted);
                double hi = max(sorted);

                int cx = marginLeft + i * slotWidth + slotWidth / 2;
                int yLo = toY(lo, globalMin, globalMax, marginTop, plotH);
                int yHi = toY(hi, globalMin, globalMax, marginTop, plotH);
                int yQ1 = toY(q1, globalMin, globalMax, marginTop, plotH);
                int yQ3 = toY(q3, globalMin, globalMax, marginTop, plotH);
                int yMed = toY(med, globalMin, globalMax, marginTop, plotH);

                g2.setColor(Color.BLACK);
                g2.drawLine(cx, yHi, cx, yQ3);
                g2.drawLine(cx, yQ1, cx, yLo);
                int boxWidth = slotWidth / 2;
                g2.setColor(new Color(200, 220, 255));
                g2.fillRect(cx - boxWidth / 2, yQ3, boxWidth, yQ1 - yQ3);
                g2.setColor(Color.BLACK);
                g2.drawRect(cx - boxWidth / 2, yQ3, boxWidth, yQ1 - yQ3);
                g2.drawLine(cx - boxWidth / 2, yMed, cx + boxWidth / 2, yMed);

                Graphics2D gr = (Graphics2D) g2.create();
                gr.translate(cx, marginTop + plotH + 12);
                gr.rotate(-Math.PI / 4);
                gr.drawString(labels[i], 0, 0);
                gr.dispose();
            }
        }

        int toY(double val, double lo, double hi, int top, int plotH) {
            return top + plotH - (int) ((val - lo) / (hi - lo) * plotH);
        }
    }

    class PieChartPanel extends JPanel {
        String[] labels;
        double[] values;
        String title;

        PieChartPanel(String[] labels, double[] values, String title) {
            this.labels = labels;
            this.values = values;
            this.title = title;
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();
            g2.setColor(Color.BLACK);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14f));
            g2.drawString(title, w / 2 - title.length() * 3, 20);

            double total = 0;
            for (double v : values) total += v;
            int size = Math.min(w, h - 60) - 40;
            int x = (w - size) / 2, y = 50;
            Color[] palette = {new Color(255, 153, 153), new Color(102, 179, 255), new Color(153, 255, 153),
                    new Color(255, 204, 102)};
            double start = 90;
            for (int i = 0; i < values.length; i++) {
                double sweep = -(values[i] / total) * 360;
                g2.setColor(palette[i % palette.length]);
                g2.fillArc(x, y, size, size, (int) start, (int) sweep);
                g2.setColor(Color.BLACK);
                g2.drawArc(x, y, size, size, (int) start, (int) sweep);

                double midAngle = Math.toRadians(start + sweep / 2.0);
                int lx = (int) (x + size / 2.0 + Math.cos(midAngle) * size * 0.35);
                int ly = (int) (y + size / 2.0 - Math.sin(midAngle) * size * 0.35);
                String pct = String.format("%.1f%%", values[i] / total * 100);
                g2.drawString(labels[i] + " " + pct, lx - 20, ly);

                start += sweep;
            }
        }
    }

    class HeatmapPanel extends JPanel {
        String[] labels;
        double[][] matrix;
        String title;

        HeatmapPanel(String[] labels, double[][] matrix, String title) {
            this.labels = labels;
            this.matrix = matrix;
            this.title = title;
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();
            g2.setColor(Color.BLACK);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14f));
            g2.drawString(title, w / 2 - title.length() * 3, 18);

            int n = matrix.length;
            int marginLeft = 90, marginTop = 40, marginBottom = 60, marginRight = 20;
            int gridW = w - marginLeft - marginRight;
            int gridH = h - marginTop - marginBottom;
            int cellW = gridW / n, cellH = gridH / n;

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 10f));
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double val = matrix[i][j];
                    Color c = val >= 0
                            ? new Color(255, (int) (255 * (1 - val)), (int) (255 * (1 - val)))
                            : new Color((int) (255 * (1 + val)), (int) (255 * (1 + val)), 255);
                    int cx = marginLeft + j * cellW;
                    int cy = marginTop + i * cellH;
                    g2.setColor(c);
                    g2.fillRect(cx, cy, cellW, cellH);
                    g2.setColor(Color.BLACK);
                    g2.drawRect(cx, cy, cellW, cellH);
                    String txt = String.format("%.2f", val);
                    g2.drawString(txt, cx + cellW / 2 - 12, cy + cellH / 2 + 4);
                }
                g2.drawString(labels[i], 5, marginTop + i * cellH + cellH / 2 + 4);
                Graphics2D gr = (Graphics2D) g2.create();
                gr.translate(marginLeft + i * cellW + cellW / 2.0, marginTop + gridH + 15);
                gr.rotate(-Math.PI / 4);
                gr.drawString(labels[i], 0, 0);
                gr.dispose();
            }
        }
    }

    class Regression3DPanel extends JPanel {
        double[] xs, ys, zs, ratings;
        double[][] gridX, gridY, gridZ;
        String title;

        Regression3DPanel(double[] xs, double[] ys, double[] zs, double[] ratings,
                           double[][] gridX, double[][] gridY, double[][] gridZ, String title) {
            this.xs = xs; this.ys = ys; this.zs = zs; this.ratings = ratings;
            this.gridX = gridX; this.gridY = gridY; this.gridZ = gridZ;
            this.title = title;
        }

        Point project(double xn, double yn, double zn, int cx, int cy) {
            double ang = Math.toRadians(30);
            int px = cx + (int) ((xn - yn) * Math.cos(ang));
            int py = cy - (int) ((xn + yn) * Math.sin(ang)) - (int) zn;
            return new Point(px, py);
        }

        double norm(double v, double lo, double hi, double scale) {
            if (hi - lo < 1e-9) return scale / 2;
            return (v - lo) / (hi - lo) * scale;
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();
            g2.setColor(Color.BLACK);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 13f));
            g2.drawString(title, 15, 20);

            int cx = w / 2, cy = h - 100;
            double scale = 180;

            double minX = min(xs), maxX = max(xs);
            double minY = min(ys), maxY = max(ys);
            double minZ = min(zs), maxZ = max(zs);
            double minR = min(ratings), maxR = max(ratings);

            g2.setColor(Color.DARK_GRAY);
            Point origin = project(0, 0, 0, cx, cy);
            Point xAxis = project(scale, 0, 0, cx, cy);
            Point yAxis = project(0, scale, 0, cx, cy);
            Point zAxis = project(0, 0, scale, cx, cy);
            g2.drawLine(origin.x, origin.y, xAxis.x, xAxis.y);
            g2.drawLine(origin.x, origin.y, yAxis.x, yAxis.y);
            g2.drawLine(origin.x, origin.y, zAxis.x, zAxis.y);
            g2.drawString("Time", xAxis.x, xAxis.y);
            g2.drawString("Ingredients", yAxis.x, yAxis.y);
            g2.drawString("Calories", zAxis.x, zAxis.y);

            g2.setColor(new Color(180, 180, 180, 150));
            int gn = gridX.length;
            for (int i = 0; i < gn; i++) {
                for (int j = 0; j < gn - 1; j++) {
                    Point p1 = project(norm(gridX[i][j], minX, maxX, scale), norm(gridY[i][j], minY, maxY, scale),
                            norm(gridZ[i][j], minZ, maxZ, scale), cx, cy);
                    Point p2 = project(norm(gridX[i][j + 1], minX, maxX, scale), norm(gridY[i][j + 1], minY, maxY, scale),
                            norm(gridZ[i][j + 1], minZ, maxZ, scale), cx, cy);
                    g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
            for (int j = 0; j < gn; j++) {
                for (int i = 0; i < gn - 1; i++) {
                    Point p1 = project(norm(gridX[i][j], minX, maxX, scale), norm(gridY[i][j], minY, maxY, scale),
                            norm(gridZ[i][j], minZ, maxZ, scale), cx, cy);
                    Point p2 = project(norm(gridX[i + 1][j], minX, maxX, scale), norm(gridY[i + 1][j], minY, maxY, scale),
                            norm(gridZ[i + 1][j], minZ, maxZ, scale), cx, cy);
                    g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }

            for (int i = 0; i < xs.length; i++) {
                Point p = project(norm(xs[i], minX, maxX, scale), norm(ys[i], minY, maxY, scale),
                        norm(zs[i], minZ, maxZ, scale), cx, cy);
                g2.setColor(ratingToColor(ratings[i], minR, maxR));
                g2.fillOval(p.x - 5, p.y - 5, 10, 10);
                g2.setColor(Color.BLACK);
                g2.drawOval(p.x - 5, p.y - 5, 10, 10);
            }
        }
    }

    public void plotCuisineAnalysis() {
        Map<String, List<Recipe>> byCuisine = new LinkedHashMap<>();
        for (Recipe r : recipes) byCuisine.computeIfAbsent(r.cuisine, k -> new ArrayList<>()).add(r);

        List<Map.Entry<String, Double>> avgTimeList = new ArrayList<>();
        for (Map.Entry<String, List<Recipe>> e : byCuisine.entrySet()) {
            double avg = mean(e.getValue().stream().mapToDouble(r -> r.time).toArray());
            avgTimeList.add(new AbstractMap.SimpleEntry<>(e.getKey(), avg));
        }
        avgTimeList.sort(Map.Entry.comparingByValue());
        String[] barLabels = avgTimeList.stream().map(Map.Entry::getKey).toArray(String[]::new);
        double[] barValues = avgTimeList.stream().mapToDouble(Map.Entry::getValue).toArray();

        String[] cuisineOrder = byCuisine.keySet().toArray(new String[0]);
        double[][] ratingGroups = new double[cuisineOrder.length][];
        for (int i = 0; i < cuisineOrder.length; i++) {
            ratingGroups[i] = byCuisine.get(cuisineOrder[i]).stream().mapToDouble(r -> r.rating).toArray();
        }

        Map<String, Integer> diffCounts = new LinkedHashMap<>();
        for (Recipe r : recipes) diffCounts.merge(r.difficulty, 1, Integer::sum);
        String[] diffLabels = diffCounts.keySet().toArray(new String[0]);
        double[] diffValues = new double[diffLabels.length];
        for (int i = 0; i < diffLabels.length; i++) diffValues[i] = diffCounts.get(diffLabels[i]);

        String[] numericLabels = {"time", "rating", "calories", "ing_count", "complexity"};
        double[][] numericData = new double[5][recipes.size()];
        for (int i = 0; i < recipes.size(); i++) {
            Recipe r = recipes.get(i);
            numericData[0][i] = r.time;
            numericData[1][i] = r.rating;
            numericData[2][i] = r.calories;
            numericData[3][i] = r.ingredientCount;
            numericData[4][i] = r.complexityScore;
        }
        double[][] corr = new double[5][5];
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                corr[i][j] = correlation(numericData[i], numericData[j]);

        JFrame frame = new JFrame("Recipe Cuisine Analysis");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 2));
        frame.add(new BarChartPanel(barLabels, barValues, "Average Cooking Time by Cuisine", "Time (minutes)"));
        frame.add(new BoxPlotPanel(cuisineOrder, ratingGroups, "Rating Distribution by Cuisine", "Rating"));
        frame.add(new PieChartPanel(diffLabels, diffValues, "Difficulty Level Distribution"));
        frame.add(new HeatmapPanel(numericLabels, corr, "Correlation Heatmap"));
        frame.setSize(1100, 850);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void plotRegressionAnalysis(String cuisineInput, String dietaryInput) {
        List<Recipe> filtered = new ArrayList<>();
        for (Recipe r : recipes) {
            if (r.cuisine.equalsIgnoreCase(cuisineInput) && r.dietary.equalsIgnoreCase(dietaryInput)) filtered.add(r);
        }
        if (filtered.isEmpty()) {
            System.out.println("No recipes found for " + cuisineInput + " cuisine with " + dietaryInput + " dietary preference.");
            return;
        }
        if (filtered.size() <= 1) {
            System.out.println("Not enough data points for regression analysis.");
            return;
        }

        double[][] x = new double[filtered.size()][2];
        double[] y = new double[filtered.size()];
        for (int i = 0; i < filtered.size(); i++) {
            x[i][0] = filtered.get(i).time;
            x[i][1] = filtered.get(i).ingredientCount;
            y[i] = filtered.get(i).calories;
        }
        double[] beta = linearRegression(x, y);

        double[] timeCol = Arrays.stream(x).mapToDouble(r -> r[0]).toArray();
        double[] ingCol = Arrays.stream(x).mapToDouble(r -> r[1]).toArray();
        double timeMin = min(timeCol), timeMax = max(timeCol);
        double ingMin = min(ingCol), ingMax = max(ingCol);

        int gridN = 10;
        double[][] gridX = new double[gridN][gridN];
        double[][] gridY = new double[gridN][gridN];
        double[][] gridZ = new double[gridN][gridN];
        for (int i = 0; i < gridN; i++) {
            for (int j = 0; j < gridN; j++) {
                double t = timeMin + (timeMax - timeMin) * i / (gridN - 1);
                double ing = ingMin + (ingMax - ingMin) * j / (gridN - 1);
                gridX[i][j] = t;
                gridY[i][j] = ing;
                gridZ[i][j] = beta[0] + beta[1] * t + beta[2] * ing;
            }
        }

        double[] ratings = filtered.stream().mapToDouble(r -> r.rating).toArray();
        double[] xs = timeCol, ys = ingCol, zs = y;

        JFrame frame = new JFrame("3D Regression: " + cuisineInput + " (" + dietaryInput + ")");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new Regression3DPanel(xs, ys, zs, ratings, gridX, gridY, gridZ,
                "3D Regression: Time & Ingredients vs Calories - " + cuisineInput + " (" + dietaryInput + ")"));
        frame.setSize(800, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        System.out.printf("%nRegression Equation: Calories = %.2f + %.2f*Time + %.2f*Ingredients%n",
                beta[0], beta[1], beta[2]);
    }

    public List<String> getCuisines() {
        List<String> list = new ArrayList<>();
        for (Recipe r : recipes) if (!list.contains(r.cuisine)) list.add(r.cuisine);
        return list;
    }

    public List<String> getDietaryOptions() {
        List<String> list = new ArrayList<>();
        for (Recipe r : recipes) if (!list.contains(r.dietary)) list.add(r.dietary);
        return list;
    }

    public int size() {
        return recipes.size();
    }

    public static void main(String[] args) {
        try {
            System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        } catch (java.io.UnsupportedEncodingException ignored) {
        }
        Scanner scanner = new Scanner(System.in);
        RecipeRecommender recommender = new RecipeRecommender();

        System.out.println("=== ENHANCED RECIPE RECOMMENDER SYSTEM ===");
        System.out.println(repeat("=", 50));

        System.out.println("\n1. DATASET OVERVIEW:");
        System.out.println("Total recipes: " + recommender.size());
        System.out.println("Cuisines available: " + String.join(", ", recommender.getCuisines()));
        System.out.println("Dietary options: " + String.join(", ", recommender.getDietaryOptions()));

        System.out.println("\n2. STATISTICAL ANALYSIS BY CUISINE:");
        recommender.analyzeCuisineStats();

        System.out.println("\n3. PERSONALIZED RECOMMENDATIONS:");
        System.out.println("Available cuisines: American, Italian, Indian, Thai, Middle Eastern, Korean, Mexican, Japanese");

        String cuisineInput;
        while (true) {
            System.out.print("Enter cuisine type (or press enter for all): ");
            String candidate = scanner.nextLine().trim();
            if (candidate.isEmpty() || recommender.getCuisines().stream().anyMatch(c -> c.equalsIgnoreCase(candidate))) {
                cuisineInput = candidate;
                break;
            }
            System.out.println("Invalid cuisine. Please choose from available options.");
        }

        Integer maxTime = null;
        while (true) {
            System.out.print("Enter maximum cooking time in minutes (or press enter for all): ");
            String timeInput = scanner.nextLine().trim();
            if (timeInput.isEmpty()) break;
            try {
                int val = Integer.parseInt(timeInput);
                if (val > 0) { maxTime = val; break; }
                System.out.println("Please enter a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        String dietaryInput;
        while (true) {
            System.out.print("Enter dietary preference (Vegetarian/Non-Vegetarian or press enter for all): ");
            dietaryInput = scanner.nextLine().trim();
            if (dietaryInput.isEmpty() || dietaryInput.equals("Vegetarian") || dietaryInput.equals("Non-Vegetarian")) break;
            System.out.println("Please enter 'Vegetarian' or 'Non-Vegetarian'");
        }

        Integer maxCalories = null;
        while (true) {
            System.out.print("Enter maximum calories (or press enter for no limit): ");
            String calInput = scanner.nextLine().trim();
            if (calInput.isEmpty()) break;
            try {
                int val = Integer.parseInt(calInput);
                if (val > 0) { maxCalories = val; break; }
                System.out.println("Please enter a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        List<Recipe> recommendations = recommender.recommendRecipes(
                cuisineInput.isEmpty() ? null : cuisineInput,
                maxTime,
                dietaryInput.isEmpty() ? null : dietaryInput,
                3.5,
                maxCalories);

        System.out.println("\n4. RECOMMENDATION RESULTS:");
        System.out.println("Found " + recommendations.size() + " recipes matching your criteria:");

        if (!recommendations.isEmpty()) {
            int idx = 1;
            for (Recipe recipe : recommendations.subList(0, Math.min(10, recommendations.size()))) {
                System.out.printf("%n%d. %s \u2b50%.1f%n", idx, recipe.name, recipe.rating);
                System.out.printf("   \ud83d\udd52 %dmin | \ud83e\udd57 %s | \ud83d\udd25 %d cal%n", recipe.time, recipe.dietary, recipe.calories);
                System.out.printf("   \ud83c\udfaf Difficulty: %s | \ud83c\udf7d\ufe0f Ingredients: %d%n", recipe.difficulty, recipe.ingredientCount);
                System.out.printf("   \ud83d\udcca Recommendation Score: %.2f%n", recipe.recommendationScore);
                System.out.println("   \ud83e\udd6c Ingredients: " + String.join(", ", recipe.ingredients));
                idx++;
            }

            System.out.print("\nWould you like YouTube links for top 3 recipes? (yes/no): ");
            String wantVideo = scanner.nextLine().trim().toLowerCase();
            if (wantVideo.equals("yes")) {
                System.out.println("\n5. YOUTUBE COOKING LINKS:");
                idx = 1;
                for (Recipe recipe : recommendations.subList(0, Math.min(3, recommendations.size()))) {
                    System.out.println(idx + ". " + recipe.name + ": " + recommender.getYoutubeSearchLink(recipe.name));
                    idx++;
                }
            }
        } else {
            System.out.println("No recipes found matching your criteria. Try relaxing some filters.");
        }

        System.out.println("\n6. NUTRITIONAL ANALYSIS:");
        NutritionalAnalysis nutrition = recommender.getNutritionalAnalysis();
        System.out.printf("Average calories per recipe: %.1f%n", nutrition.avgCalories);
        System.out.printf("Average ingredients per recipe: %.1f%n", nutrition.avgIngredients);
        System.out.println("\nTop 5 Healthiest Recipes:");
        for (Recipe r : nutrition.healthiestRecipes) {
            System.out.printf("%-25s %-16s %8d %8.1f %10.2f%n", r.name, r.cuisine, r.calories, r.rating, r.healthScore);
        }

        System.out.println("\n7. COOKING TIME PREDICTION:");
        if (!recommendations.isEmpty()) {
            Recipe sample = recommendations.get(0);
            int predTime = recommender.predictCookingTime(sample.difficultyEncoded, sample.ingredientCount);
            System.out.println("Based on similar recipes, '" + sample.name + "' should take approximately " + predTime + " minutes");
        }

        System.out.println("\n8. DATA VISUALIZATION:");
        System.out.println("Generating comprehensive analysis plots...");
        recommender.plotCuisineAnalysis();

        if (!cuisineInput.isEmpty() && !dietaryInput.isEmpty()) {
            System.out.println("\n9. ADVANCED REGRESSION ANALYSIS:");
            System.out.println("Generating 3D regression plot...");
            recommender.plotRegressionAnalysis(cuisineInput, dietaryInput);
        }

        System.out.println("\n" + repeat("=", 50));
        System.out.println("Thank you for using the Enhanced Recipe Recommender System!");
        System.out.println(repeat("=", 50));

        scanner.close();
    }
}