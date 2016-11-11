class CreateMembres < ActiveRecord::Migration
  def change
    create_table :membres do |t|
      t.string :email
      t.string :name
      t.string :password
      t.decimal :lat
      t.decimal :lon

      t.timestamps null: false
    end
  end
end
