json.array!(@membres) do |membre|
  json.extract! membre, :id, :email, :name, :password, :lat, :lon
  json.url membre_url(membre, format: :json)
end
