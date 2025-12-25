import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.CategoryType;

class Product {
    private int id;
    private int price;
    private int quantity;
    private String productName;
    private CategoryType category;
    private Supplier supplier;

    public Product(int id, String productName, int price, int quantity, CategoryType cat, Supplier supplier) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.category = cat;
        this.supplier = supplier;

    }

    public CategoryType getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", supplier='" + supplier.getFullname() + '\'' +
                '}';
    }

}

class Supplier

{
    private int id;
    private String phonenumber;
    private String fullName;

    Supplier(int id, String number, String fullname) {
        this.id = id;
        this.fullName = fullname;
        this.phonenumber = number;

    }

    public String getFullname() {
        return fullName;
    }

    public void setFullname(String fullname) {
        this.fullName = fullname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "Supplier{id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phonenumber + '\'' +
                '}';

    }

}

class InventoryService {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public List<Product> filterByCategory(CategoryType category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory() == category) {
                result.add(p);
            }
        }
        return result;

    }

    public List<Product> filterByMinprice(int minPrice) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getPrice() >= minPrice) {

                result.add(p);
            }
        }
        return result;
    }

    public boolean productidexists(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return true;

            }

        }
        return false;
    }

}

class SupplierService {
    private List<Supplier> suppliers = new ArrayList<>();

    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);

    }

    public Supplier getSupplierById(int id) {
        for (Supplier supplier : suppliers) {
            if (supplier.getId() == id) {
                return supplier;
            }
        }
        return null;

    }

    public List<Supplier> getAllSuppliers() {
        return suppliers;
    }
}

public class project1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        InventoryService service = new InventoryService();
        SupplierService service2 = new SupplierService();

        Supplier s1 = new Supplier(1, "9034", "Suresh");
        Supplier s2 = new Supplier(2, "8123", "Ramesh");

        service2.addSupplier(s1);
        service2.addSupplier(s2);

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("\nProduct " + (i + 1));
            int id;
            while (true) {
                System.out.println("id :");
                id = sc.nextInt();
                sc.nextLine();
                if (service.productidexists(id)) {
                    System.out.println("product id already exist . try again .");

                } else {
                    break;
                }

            }

            System.out.print("Name: ");
            String name = sc.nextLine();

            int price;
            while (true) {
                System.out.print("price : ");
                price = sc.nextInt();
                sc.nextLine();

                if (price <= 0) {
                    System.out.println("price must be greater than 0");
                } else {
                    break;
                }
            }

            int quantity;
            while (true) {
                System.out.print("quantity : ");
                quantity = sc.nextInt();
                sc.nextLine();

                if (quantity < 0) {
                    System.out.println("quantity must be greater than 0 ");

                } else {
                    break;
                }
            }

            // System.out.print("Category (electronics/sports/household): ");

            CategoryType selectedCategory = null;
            while (selectedCategory == null) {
                System.out.print("Category (electronics/sports/household): ");
                String input = sc.nextLine().trim().toLowerCase();

                if (input.equals("electronics") || input.equals("e")) {
                    selectedCategory = CategoryType.ELECTRONICS;

                } else if (input.equals("sports") || input.equals("s")) {
                    selectedCategory = CategoryType.SPORTS;
                } else if (input.equals("household") || input.equals("h")) {
                    selectedCategory = CategoryType.HOUSEHOLD;
                }
                else{
                    System.out.println("invalid !!!");
                }

               /*  try {
                    selectedCategory = CategoryType.valueOf(input);

                } catch (IllegalArgumentException e) {
                    System.out.println("try again !!!");
                }/* */

            }

            Supplier selectedSupplier = null;
            while (selectedSupplier == null) {
                System.out.println("Supplier ID :");
                int supplierid = sc.nextInt();
                sc.nextLine();

                selectedSupplier = service2.getSupplierById(supplierid);
                if (selectedSupplier == null) {
                    System.out.println("invalid id . try again");

                }
            }

            Product p = new Product(id, name, price, quantity, selectedCategory, selectedSupplier);
            service.addProduct(p);

        }

        // Filter by category
       for (CategoryType category : CategoryType.values())
       {
        List<Product> ProductByCategoryType = service.filterByCategory(category);
        System.out.println(category + "Product :");
        for(Product p : ProductByCategoryType){
            System.out.println(p);

        }
       }
        // Filter by price
        List<Product> expensiveProducts = service.filterByMinprice(3000);
        System.out.println("\nExpensive products:");
        for (Product p : expensiveProducts) {
            System.out.println(p);
        }
        sc.close();
    }
}
