#define CATCH_CONFIG_MAIN  // This tells Catch to provide a main() - only do this in one cpp file
#include "catch.hpp"

#include <db.hpp>
#include <map>
#include <memory>
#include <list>

using namespace std;
struct Pentest {
  int id;
  string title;
  string risk_type;
  int time;
  float weight;
};

ostream & operator<<(ostream &o, const Pentest &d) {
    o << 
    "["  << d.id << 
    ", " << d.title <<
    ", " << d.risk_type <<
    ", " << d.time <<
    ", " << d.weight <<
    "]";
    return o;
}

TEST_CASE("Basic database object operations", "[database_c][constructors]") {

  SECTION("The database object can be created") {

    REQUIRE_NOTHROW([]() { database_c<Pentest> db; });
  }
  SECTION("Simple dependency injection works") {
    auto container = make_shared<map<int, Pentest>>();
    database_c<Pentest> db(container);
    REQUIRE_FALSE(db.get_container() == nullptr);
    REQUIRE(db.get_container() == container.get());
  }
}

TEST_CASE("Getting data from the database", "[database_c][get_all]") {
    auto container = make_shared<map<int, Pentest>>();
    database_c<Pentest> db(container);
    SECTION("simple get all is present") {
        REQUIRE_NOTHROW( db.get_all() );
    }
    SECTION("simple get all is present") {
        list<Pentest> ret_list = db.get_all();
        REQUIRE(ret_list.size() == 0);
    }
    SECTION("simple get all is present") {
        (*container)[0] = {1, "test", "testowy", 5, 0.5};
        (*container)[1] = {2, "test2", "testowy2", 7, 0.7};
        list<Pentest> ret_list = db.get_all();
        REQUIRE(db.get_container() == container.get());
        REQUIRE(db.get_container()->size() == 2);
        REQUIRE(ret_list.size() == 2);
    }
}

TEST_CASE("Getting data from the database by id", "[database_c][get_by_id]") {
    auto container = make_shared<map<int, Pentest>>();
    database_c<Pentest> db(container);

    SECTION("get by existing id") {
        Pentest newPentest = {1, "test", "testowy", 5, 0.5};
        (*container)[0] = newPentest;
        
        Pentest pentest = db.get_by_id(1);
        REQUIRE(pentest.title == "test");
    }
}

TEST_CASE("Adding data to the database", "[database_c][add]") {
    auto container = make_shared<map<int, Pentest>>();
    database_c<Pentest> db(container);

    SECTION("add new value") {
        Pentest pentest = {1, "test", "testowy", 5, 0.5};
        db.add(pentest);
        REQUIRE(db.get_container() == container.get());
        REQUIRE(db.get_container()->size() == 2);
        // REQUIRE(ret_list.size() == 2);
    }
}

SCENARIO("getting data from database","[database_c][bdd][get_all]") {
    auto container = make_shared<map<int, Pentest>>();
    database_c<Pentest> db(container);
    GIVEN("we have some data in database") {
        (*container)[0] = {1, "test", "testowy", 5, 0.5};
        (*container)[1] = {2, "test2", "testowy2", 1, 0.4};
        (*container)[2] = {3, "test3", "testowy3", 3, 0.2};

        INFO((*container)[0]);
        WHEN("we get them from the database") {
            list<Pentest> ret_list = db.get_all();

            THEN("the list should contain 3 elements") {
                CHECK(ret_list.size() == 3);
            }

            THEN("the list should contain element with title test3") {
                Pentest found;
                for (auto e:ret_list) {
                    if (e.title == "test3") found = e;
                }
                REQUIRE(found.title =="test3");
            }
        }
    }
}