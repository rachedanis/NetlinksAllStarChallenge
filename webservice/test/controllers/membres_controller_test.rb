require 'test_helper'

class MembresControllerTest < ActionController::TestCase
  setup do
    @membre = membres(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:membres)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create membre" do
    assert_difference('Membre.count') do
      post :create, membre: { email: @membre.email, lat: @membre.lat, lon: @membre.lon, name: @membre.name, password: @membre.password }
    end

    assert_redirected_to membre_path(assigns(:membre))
  end

  test "should show membre" do
    get :show, id: @membre
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @membre
    assert_response :success
  end

  test "should update membre" do
    patch :update, id: @membre, membre: { email: @membre.email, lat: @membre.lat, lon: @membre.lon, name: @membre.name, password: @membre.password }
    assert_redirected_to membre_path(assigns(:membre))
  end

  test "should destroy membre" do
    assert_difference('Membre.count', -1) do
      delete :destroy, id: @membre
    end

    assert_redirected_to membres_path
  end
end
