class MembresController < ApplicationController
  before_filter :add_cors_headers
  before_action :set_membre, only: [:show, :edit, :update, :destroy]

  def add_cors_headers
    headers['Access-Control-Allow-Origin'] = '*'
    headers['Access-Control-Allow-Methods'] = 'POST, PUT, DELETE, GET, OPTIONS'
    headers['Access-Control-Request-Method'] = '*'
    headers['Access-Control-Allow-Headers'] = 'Origin, X-Requested-With, Content-Type, Accept, Authorization'

  end

  # GET /membres
  # GET /membres.json
  def index
    @membres = Membre.all
  end

  # GET /membres/1
  # GET /membres/1.json
  def show
  end

  # GET /membres/new
  def new
    @membre = Membre.new
  end

  # GET /membres/1/edit
  def edit
  end

  def add
      @membre = Membre.new(email: params[:email], name: params[:name], password: params[:password], lat: params[:lat], lon: params[:lon])
      @membre.save

  end

  def change
    Membre.find(params[:id]).update(lat: params[:lat], lon: params[:lon])
  end


  # POST /membres
  # POST /membres.json
  def create
    @membre = Membre.new(membre_params)

    respond_to do |format|
      if @membre.save
        format.html { redirect_to @membre, notice: 'Membre was successfully created.' }
        format.json { render :show, status: :created, location: @membre }
      else
        format.html { render :new }
        format.json { render json: @membre.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /membres/1
  # PATCH/PUT /membres/1.json
  def update
    respond_to do |format|
      if @membre.update(membre_params)
        format.html { redirect_to @membre, notice: 'Membre was successfully updated.' }
        format.json { render :show, status: :ok, location: @membre }
      else
        format.html { render :edit }
        format.json { render json: @membre.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /membres/1
  # DELETE /membres/1.json
  def destroy
    @membre.destroy
    respond_to do |format|
      format.html { redirect_to membres_url, notice: 'Membre was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_membre
      @membre = Membre.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def membre_params
      params.require(:membre).permit(:email, :name, :password, :lat, :lon)
    end
end
