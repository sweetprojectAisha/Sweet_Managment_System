package MYApp_Sweet;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ManageRecipeC {
    private static ManageRecipeC instance;
    private boolean navagates_to_recipepage;
    private boolean navagates_to_feedbackpage;
    private boolean navagates_to_postpage;
    private Map<String, Recipe> recipes = new HashMap<>();
    private Map<String, Post> posts = new HashMap<>();
    private List<String> searchResults = new ArrayList<>();
    private List<Feedback> feedbackList = new ArrayList<>();
    public static ManageRecipeC getInstance() {
        if (instance == null) {
            instance = new ManageRecipeC();
        }
        return instance;
    }
    public boolean isNavagates_to_recipepage() {
        return navagates_to_recipepage;
    }

    public void setNavagates_to_recipepage(boolean navagates_to_recipepage) {
        this.navagates_to_recipepage = navagates_to_recipepage;
    }
    public boolean isNavagates_to_feedbackpage() {
        return navagates_to_feedbackpage;
    }

    public void setNavagates_to_feedbackpage(boolean navagates_to_feedbackpage) {
        this.navagates_to_feedbackpage = navagates_to_feedbackpage;
    }
    public boolean isNavagates_to_postpage() {
        return navagates_to_postpage;
    }

    public void setNavagates_to_postpage(boolean navagates_to_postpage) {
        this.navagates_to_postpage = navagates_to_postpage;
    }
    public void addRecipe(String title, String ingredients, String preparation, String servings, String cookingTime, List<String> tags) {
        List<String> ingredientsList = new ArrayList<>();
        for (String ingredient : ingredients.split(", ")) {
            ingredientsList.add(ingredient);
        }
        Recipe recipe = new Recipe(title, ingredientsList, preparation, servings, cookingTime, tags);
        recipes.put(title, recipe);
    }
    public ManageRecipeC.Recipe getRecipe(String title) {
        return recipes.get(title);
    }
    public void updateRecipe(String oldTitle, String newTitle, List<String> ingredients, String preparation, String servings, String cookingTime, List<String> tags) {
        Recipe existingRecipe = recipes.get(oldTitle);
        if (existingRecipe != null) {
            recipes.remove(oldTitle);
            recipes.put(newTitle, new Recipe(newTitle, ingredients, preparation, servings, cookingTime, tags));
        } else {
            throw new IllegalArgumentException("Recipe with title '" + oldTitle + "' does not exist.");
        }
    }
    public void deleteRecipe(String title) {
        if (recipes.containsKey(title)){
        recipes.remove(title);}
        else {
            throw new IllegalArgumentException("Recipe with title '" + title + "' does not exist.");
        }
    }
    public List<Recipe> searchRecipes(String keyword) {
        return recipes.values().stream()
                .filter(recipe -> recipe.getTitle().contains(keyword) ||
                        recipe.getIngredients().contains(keyword) ||
                        recipe.getTags().contains(keyword))
                .collect(Collectors.toList());
    }

    public List<String> getSearchResults() {
        return searchResults;
    }
    public void addFeedback(String recipeTitle, String comment, int rating) {
        feedbackList.add(new Feedback(recipeTitle, comment, rating));

    }
    public void respondToFeedback(String title, String response) {
        Feedback feedbackToUpdate = feedbackList.stream()
                .filter(feedback -> feedback.getRecipeTitle().equals(title))
                .findFirst()
                .orElse(null);
        if (feedbackToUpdate != null) {
            feedbackToUpdate.setResponse(response);
            System.out.println("Feedback response updated for title: " + title);
        } else {
            System.err.println("Feedback with title " + title + " not found.");
            throw new IllegalStateException("Feedback with title " + title + " not found.");
        }
    }
    public void deleteFeedback(String comment) {
        boolean removed = feedbackList.removeIf(feedback -> feedback.getComment().equals(comment));

        if (removed) {
            System.out.println("Feedback with comment '" + comment + "' has been removed.");
        } else {
            System.err.println("Feedback with comment '" + comment + "' was not found.");
            throw new IllegalArgumentException("Feedback with comment '" + comment + "' does not exist.");
        }
    }





    public boolean isFeedbackSubmitted() {
        return !feedbackList.isEmpty();
    }

    public List<Feedback> getFeedbackList() {
        if (feedbackList == null) {
            feedbackList = new ArrayList<>();
        }
        return feedbackList;
    }

    public void addPost(String title, String content, List<String> tags) {
        posts.put(title, new Post(title, content, tags));
    }

    public void updatePost(String oldTitle, String newTitle, String newContent, List<String> newTags) {
        Post existingPost = posts.get(oldTitle);
        if (existingPost != null) {
            posts.remove(oldTitle);
            posts.put(newTitle, new Post(newTitle, newContent, newTags));
        } else {
            throw new IllegalArgumentException("Post with title '" + oldTitle + "' does not exist.");
        }
    }


    public void deletePost(String title) {
        if (posts.containsKey(title)) {
            posts.remove(title);
        } else {
            throw new IllegalArgumentException("Post with title '" + title + "' does not exist.");
        }
    }

    public Post getPost(String title) {
        return posts.get(title);
    }

    public List<Post> searchPosts(String keyword) {
        return posts.values().stream()
                .filter(post -> post.getTitle().contains(keyword) ||
                        post.getContent().contains(keyword) ||
                        post.getTags().contains(keyword))
                .collect(Collectors.toList());
    }
    public static class Post {
        private String title;
        private String content;
        private List<String> tags;

        public Post(String title, String content, List<String> tags) {
            this.title = title;
            this.content = content;
            this.tags = tags;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }
    }
    public static class Feedback {
        private String recipeTitle;
        private String comment;
        private int rating;
        private String response;
        public Feedback(String recipeTitle, String comment, int rating) {
            this.recipeTitle = recipeTitle;
            this.comment = comment;
            this.rating = rating;
            this.response = "";
        }

        public String getRecipeTitle() {
            return recipeTitle;
        }

        public void setRecipeTitle(String recipeTitle) {
            this.recipeTitle = recipeTitle;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }
        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }
    }

    public static class Recipe {
        private String title;
        private List<String> ingredients;
        private String preparation;
        private String servings;
        private String cookingTime;
        private List<String> tags;

        public Recipe(String title, List<String> ingredients, String preparation, String servings, String cookingTime, List<String> tags) {
            this.title = title;
            this.ingredients = ingredients;
            this.preparation = preparation;
            this.servings = servings;
            this.cookingTime = cookingTime;
            this.tags = tags;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<String> ingredients) {
            this.ingredients = ingredients;
        }

        public String getPreparation() {
            return preparation;
        }

        public void setPreparation(String preparation) {
            this.preparation = preparation;
        }

        public String getServings() {
            return servings;
        }

        public void setServings(String servings) {
            this.servings = servings;
        }

        public String getCookingTime() {
            return cookingTime;
        }

        public void setCookingTime(String cookingTime) {
            this.cookingTime = cookingTime;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }
    }
}
